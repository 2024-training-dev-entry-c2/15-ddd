package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.property.makemortgage.MakeMortgageRequest;
import com.monopoly.monopoly_managment.application.property.makemortgage.MakeMortgageUseCase;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/makemortgage")
public class MakeMortgage {
  private final MakeMortgageUseCase makeMortgageUseCase;

  public MakeMortgage(MakeMortgageUseCase makeMortgageUseCase) {
    this.makeMortgageUseCase = makeMortgageUseCase;
  }

  @PostMapping
  public Mono<PropertyResponse> execute(@RequestBody MakeMortgageRequest request) {
    return makeMortgageUseCase.execute(request);
  }
}
