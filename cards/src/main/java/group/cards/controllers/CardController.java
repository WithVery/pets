package group.cards.controllers;

import group.cards.domain.model.CardEntity;
import group.cards.domain.model.CardStatus;
import group.cards.domain.model.UserEntity;
import group.cards.dto.CardDto;
import group.cards.mappers.Mapper;
import group.cards.mappers.impl.CardMapperImpl;
import group.cards.service.CardService;
import group.cards.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


import static group.cards.config.MainConfiguration.WEBROOT;

@RestController
@RequestMapping(WEBROOT)
public class CardController {

  private CardService cardService;
  private UserService userService;
  private Mapper<CardDto, CardEntity> cardMapper;

  public CardController(CardService cardService, UserService userService, Mapper<CardDto, CardEntity> cardMapper) {
    this.cardService = cardService;
    this.userService = userService;
    this.cardMapper = cardMapper;
  }

  @GetMapping(path = "/cards")
  public ResponseEntity<Iterable<CardEntity>> listCards(@AuthenticationPrincipal UserDetails userDetails) {
    if(userDetails == null) {
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    UserEntity user = userService.findByUsername(userDetails.getUsername());
    if(user == null) {
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(cardService.findByUserId(user.getId()), HttpStatus.OK);
  }

  @PostMapping(path = "/cards")
  public ResponseEntity<CardDto> createCard(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CardDto cardCreateRequest) {

    UserEntity userCreator = userService.findByUsername(userDetails.getUsername());
    if(!userCreator.isAdmin()) { // only admin can create cards!
      return new ResponseEntity<>(
          new CardDto(),
          HttpStatus.METHOD_NOT_ALLOWED);
    }

    UserEntity cardOwner = userService.findById(cardCreateRequest.getUserId());
    if(cardOwner == null) {
      new ResponseEntity<>(
          new CardDto(),
          HttpStatus.BAD_REQUEST
      );
    }

    return new ResponseEntity<>(
        cardMapper.mapFrom(
          cardService.createNewCard(cardOwner)
        ),
        HttpStatus.CREATED
    );
  }

  @PatchMapping(path = "/cards/{id}")
  public ResponseEntity<CardDto> setCardState(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long cardId, @RequestBody CardDto cardDto) {
    UserEntity user = userService.findByUsername(userDetails.getUsername());
    if(user == null) {
      return new ResponseEntity<>(
          new CardDto(),
          HttpStatus.BAD_REQUEST
      );
    }

    CardEntity card = cardMapper.mapTo(cardDto);
    card.setId(cardId);

    if(card.isActive() && !user.isAdmin()) { // only Admin can activate cards
      return new ResponseEntity<>(
          new CardDto(),
          HttpStatus.METHOD_NOT_ALLOWED
      );
    }

    if(!user.isAdmin() &&
        (
        card.getStatus() != CardStatus.BLOCKED ||
        !card.getUser().equals(user)
        )
      ) { // User only can block the cards
      return new ResponseEntity<>(
          new CardDto(),
          HttpStatus.METHOD_NOT_ALLOWED
      );
    }

    card = cardService.save(card);

    return new ResponseEntity<>(
        cardMapper.mapFrom(card),
        HttpStatus.OK
    );
  }
}
