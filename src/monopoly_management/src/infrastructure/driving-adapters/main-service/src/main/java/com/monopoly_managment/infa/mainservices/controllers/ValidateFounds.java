package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import com.monopoly.monopoly_managment.application.banck_account.validatefounds.ValidateFoundsRequest;
import com.monopoly.monopoly_managment.application.banck_account.validatefounds.ValidateFoundsUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/validatefounds")
public class ValidateFounds {
  private final ValidateFoundsUseCase validateFoundsUseCase;

  public ValidateFounds(ValidateFoundsUseCase validateFoundsUseCase) {
    this.validateFoundsUseCase = validateFoundsUseCase;
  }

  @PostMapping
  public Mono<BankAccountResponse> execute(@RequestBody ValidateFoundsRequest request) {
    return validateFoundsUseCase.execute(request);
  }
}
