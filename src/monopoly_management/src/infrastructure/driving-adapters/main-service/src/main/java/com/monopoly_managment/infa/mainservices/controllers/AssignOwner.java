package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.property.assignedowner.AssignOwnerRequest;
import com.monopoly.monopoly_managment.application.property.assignedowner.AssignOwnerUseCase;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/assignowner")
public class AssignOwner {
  private final AssignOwnerUseCase assignOwnerUseCase;

  public AssignOwner(AssignOwnerUseCase assignOwnerUseCase) {
    this.assignOwnerUseCase = assignOwnerUseCase;
  }

  @PostMapping
  public Mono<PropertyResponse> execute(@RequestBody AssignOwnerRequest request) {
    return assignOwnerUseCase.execute(request);
  }
}
