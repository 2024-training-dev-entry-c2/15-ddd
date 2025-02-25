package com.monopoly_managment.infa.mainservices.controllers;


import com.monopoly.monopoly_managment.application.property.demolishimprovement.DemolishImprovementRequest;
import com.monopoly.monopoly_managment.application.property.demolishimprovement.DemolishImprovementUseCase;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/demolishimprovement")
public class DemolishImprovement {
  private final DemolishImprovementUseCase demolishImprovementUseCase;

  public DemolishImprovement(DemolishImprovementUseCase demolishImprovementUseCase) {
    this.demolishImprovementUseCase = demolishImprovementUseCase;
  }

  @PostMapping
  public Mono<PropertyResponse> execute(@RequestBody DemolishImprovementRequest request) {
    return demolishImprovementUseCase.execute(request);
  }
}
