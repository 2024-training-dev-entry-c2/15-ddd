package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.property.modifyowner.ModifyOwnerRequest;
import com.monopoly.monopoly_managment.application.property.modifyowner.ModifyOwnerUseCase;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/modifyowner")
public class ModifyOwner {
  private final ModifyOwnerUseCase modifyOwnerUseCase;

  public ModifyOwner(ModifyOwnerUseCase modifyOwnerUseCase) {
    this.modifyOwnerUseCase = modifyOwnerUseCase;
  }

  @PostMapping
  public Mono<PropertyResponse> execute(@RequestBody ModifyOwnerRequest request) {
    return modifyOwnerUseCase.execute(request);
  }
}
