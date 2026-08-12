package group.cards.controllers;

import group.cards.domain.model.TransactionEntity;
import group.cards.domain.model.UserEntity;
import group.cards.dto.TransactionDto;
import group.cards.mappers.Mapper;
import group.cards.service.TransactionService;
import group.cards.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

import static group.cards.config.MainConfiguration.WEBROOT;

@RestController
@RequestMapping(WEBROOT + "/transactions")
public class TransactionController {

  private TransactionService transactionService;
  private Mapper<TransactionEntity, TransactionDto> transactionMapper;
  private UserService userService;

  public TransactionController(TransactionService transactionService, Mapper<TransactionEntity, TransactionDto> transactionMapper, UserService userService) {
    this.transactionService = transactionService;
    this.transactionMapper = transactionMapper;
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<TransactionDto>> findTransactions(@AuthenticationPrincipal UserDetails userDetails) {
    if(userDetails == null) {
      return new ResponseEntity<>(new LinkedList<>(), HttpStatus.BAD_REQUEST);
    }

    UserEntity user = userService.findByUsername(userDetails.getUsername());

    if (user == null) {
      return new ResponseEntity<>(new LinkedList<>(), HttpStatus.BAD_REQUEST);
    }

    List<TransactionEntity> txE = transactionService.findTransactions(user.getId());
    List<TransactionDto> txD =
      txE.stream().map(tx -> transactionMapper.mapTo(tx)).toList();

    return new ResponseEntity<>(
        txD,
        HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<TransactionDto> makeTransaction(@RequestBody TransactionDto transaction) {
    TransactionEntity savedTx = transactionService.transferMoney(
        transaction.getCardFromId(),
        transaction.getCardToId(),
        transaction.getAmount()
    );

    return new ResponseEntity<>(
        transactionMapper.mapTo(savedTx),
        HttpStatus.CREATED
    );
  }
}
