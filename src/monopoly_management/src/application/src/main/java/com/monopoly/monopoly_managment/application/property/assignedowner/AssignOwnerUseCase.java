package com.monopoly.monopoly_managment.application.property.assignedowner;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import reactor.core.publisher.Mono;

public class AssignOwnerUseCase implements ICommandUseCase<AssignOwnerRequest, Mono<PropertyResponse>> {
private final IEventsRepository repository;

  public AssignOwnerUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PropertyResponse> execute(AssignOwnerRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        Property property = Property.from(request.getAggregateId(), events);
        property.assignedOwner(request.getOwnerId(),request.getPropertyId() );
        property.getUncommittedEvents().forEach(repository::save);
        property.markEventsAsCommitted();
        return PropertyMapper.toPropertyResponse(property);
      });
  }
}
