package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.property.madeimprovement.MadeImprovementRequest;
import com.monopoly.monopoly_managment.application.property.madeimprovement.MadeImprovementUseCase;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/makeimprovement")
public class MakeImprovement {
  private final MadeImprovementUseCase madeImprovementUseCase;

  public MakeImprovement(MadeImprovementUseCase madeImprovementUseCase) {
    this.madeImprovementUseCase = madeImprovementUseCase;
  }

  @PostMapping
  public Mono<PropertyResponse> execute(@RequestBody MadeImprovementRequest request) {
    return madeImprovementUseCase.execute(request);
  }
}
