package com.monopoly.monopoly_managment.application.property.modifyowner;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class ModifyOwnerUseCase implements ICommandUseCase<ModifyOwnerRequest, Mono<PropertyResponse>> {
  private final IEventsRepository repository;

  public ModifyOwnerUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PropertyResponse> execute(ModifyOwnerRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        Property property = Property.from(request.getAggregateId(), events);
        property.modifiedOwner(request.getOwnerId(), request.getPropertyId(), request.getPreviousOwnerId());
        property.getUncommittedEvents().forEach(repository::save);
        property.markEventsAsCommitted();
        return PropertyMapper.toPropertyResponse(property);
      });
  }
}
