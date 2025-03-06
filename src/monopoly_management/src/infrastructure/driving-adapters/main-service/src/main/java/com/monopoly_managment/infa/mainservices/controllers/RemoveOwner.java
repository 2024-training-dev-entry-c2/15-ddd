package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.property.removedowner.RemoveOwnerRequest;
import com.monopoly.monopoly_managment.application.property.removedowner.RemoveOwnerUseCase;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/removeOwner")
public class RemoveOwner {
  private final RemoveOwnerUseCase removeOwnerUseCase;

  public RemoveOwner(RemoveOwnerUseCase removeOwnerUseCase) {
    this.removeOwnerUseCase = removeOwnerUseCase;
  }

  @PostMapping
  public Mono<PropertyResponse> removeOwner(@RequestBody RemoveOwnerRequest request) {
    return removeOwnerUseCase.execute(request);
  }
}
