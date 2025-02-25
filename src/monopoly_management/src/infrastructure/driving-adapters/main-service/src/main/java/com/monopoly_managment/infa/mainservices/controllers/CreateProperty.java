package com.monopoly_managment.infa.mainservices.controllers;

import com.monopoly.monopoly_managment.application.property.createproperty.CreatePropertyRequest;
import com.monopoly.monopoly_managment.application.property.createproperty.CreatePropertyUseCase;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/createproperty")
public class CreateProperty {
  private final CreatePropertyUseCase createPropertyUseCase;

  public CreateProperty(CreatePropertyUseCase createPropertyUseCase) {
    this.createPropertyUseCase = createPropertyUseCase;
  }

  @PostMapping
  public Mono<PropertyResponse> execute(@RequestBody CreatePropertyRequest request) {
    return createPropertyUseCase.execute(request);
  }

}
