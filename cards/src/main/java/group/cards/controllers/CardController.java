package group.cards.controllers;

import group.cards.domain.model.CardEntity;
import group.cards.domain.model.CardStatus;
import group.cards.domain.model.UserEntity;
import group.cards.service.CardService;
import group.cards.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;


import static group.cards.config.MainConfiguration.WEBROOT;
import static group.cards.domain.model.CardEntity.createNewCard;
import static group.cards.service.impl.CustomUserDetailsService.getCurrentUser;

@RestController
@RequestMapping(WEBROOT)
public class CardController {

  private CardService cardService;
  private UserService userService;

  public CardController(CardService cardService, UserService userService) {
    this.cardService = cardService;
    this.userService = userService;
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
  public CardEntity createCard(@AuthenticationPrincipal UserDetails userDetails) {
    UserEntity user = userService.findByUsername(userDetails.getUsername());

    return cardService.createNewCard(user);
  }
}
