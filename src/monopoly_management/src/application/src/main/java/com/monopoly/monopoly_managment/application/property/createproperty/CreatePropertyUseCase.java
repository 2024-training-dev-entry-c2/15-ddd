package com.monopoly.monopoly_managment.application.property.createproperty;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class CreatePropertyUseCase implements ICommandUseCase<CreatePropertyRequest, Mono<PropertyResponse>> {
  private final IEventsRepositoryPort eventsRepository;

  public CreatePropertyUseCase(IEventsRepositoryPort eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<PropertyResponse> execute(CreatePropertyRequest request) {
    Property property = new Property();
    property.createdProperty(request.getContract(), request.getMortgage(), request.getOwner(),request.getUpdate() ,request.getTitle(),request.getPrice() , request.getColorGroup());
    property.getUncommittedEvents().forEach(eventsRepository::save);
    property.markEventsAsCommitted();
    return Mono.just(PropertyMapper.toPropertyResponse(property));
  }
}
