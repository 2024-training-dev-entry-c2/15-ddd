package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.banck_account.novalidateionfounds.NoValidationFoundsUseCase;
import com.monopoly.monopoly_managment.application.banck_account.novalidateionfounds.NoValididationFoundsRequest;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/novalidatefounds")
public class NoValidateFounds {
  private final NoValidationFoundsUseCase noValidationFoundsUseCase;

  public NoValidateFounds(NoValidationFoundsUseCase noValidationFoundsUseCase) {
    this.noValidationFoundsUseCase = noValidationFoundsUseCase;
  }

  @PostMapping
  public Mono<BankAccountResponse> execute(@RequestBody NoValididationFoundsRequest request) {
    return noValidationFoundsUseCase.execute(request);
  }
}
