package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.property.cancelmortgage.CancelMortgageRequest;
import com.monopoly.monopoly_managment.application.property.cancelmortgage.CancelMortgageUseCase;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cancelmortgage")
public class CancelMortgage {
  private final CancelMortgageUseCase cancelMortgageUseCase;

  public CancelMortgage(CancelMortgageUseCase cancelMortgageUseCase) {
    this.cancelMortgageUseCase = cancelMortgageUseCase;
  }

  @PostMapping
  public Mono<PropertyResponse> execute(@RequestBody CancelMortgageRequest request) {
    return cancelMortgageUseCase.execute(request);
  }

}
