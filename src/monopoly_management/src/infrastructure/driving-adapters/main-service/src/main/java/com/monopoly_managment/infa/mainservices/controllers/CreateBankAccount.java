package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.banck_account.createbankaccount.CreateBankAccountRequest;
import com.monopoly.monopoly_managment.application.banck_account.createbankaccount.CreateBankAccountUseCase;
import com.monopoly.monopoly_managment.application.banck_account.shared.BankAccountResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/createbankaccount")
public class CreateBankAccount {
  private final CreateBankAccountUseCase useCase;

  public CreateBankAccount(CreateBankAccountUseCase createBankAccountUseCase) {
    this.useCase = createBankAccountUseCase;
  }

  @PostMapping
  public Mono<BankAccountResponse> execute(@RequestBody CreateBankAccountRequest request) {
    return useCase.execute(request);
  }
}
