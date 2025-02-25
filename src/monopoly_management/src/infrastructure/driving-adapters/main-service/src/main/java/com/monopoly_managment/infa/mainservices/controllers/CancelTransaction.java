package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.banck_account.canceltransaction.CancelTransactionRequest;
import com.monopoly.monopoly_managment.application.banck_account.canceltransaction.CancelTransactionUseCase;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/cancel-transaction")
public class CancelTransaction {
  private final CancelTransactionUseCase cancelTransactionUseCase;

  public CancelTransaction(CancelTransactionUseCase cancelTransactionUseCase) {
    this.cancelTransactionUseCase = cancelTransactionUseCase;
  }

  @PostMapping
  public Mono<BankAccountResponse> execute(@RequestBody CancelTransactionRequest request) {
    return cancelTransactionUseCase.execute(request);
  }

}
