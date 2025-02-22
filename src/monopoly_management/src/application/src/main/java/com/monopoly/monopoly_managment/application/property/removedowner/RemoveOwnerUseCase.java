package com.monopoly.monopoly_managment.application.property.removedowner;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class RemoveOwnerUseCase implements ICommandUseCase<RemoveOwnerRequest, Mono<PropertyResponse>> {
  private final IEventsRepository repository;

  public RemoveOwnerUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PropertyResponse> execute(RemoveOwnerRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        Property property = Property.from(request.getAggregateId(), events);
        property.removedOwner(request.getOwnerId(), request.getOwnerId());
        property.getUncommittedEvents().forEach(repository::save);
        property.markEventsAsCommitted();
        return PropertyMapper.toPropertyResponse(property);
      });
  }
}
