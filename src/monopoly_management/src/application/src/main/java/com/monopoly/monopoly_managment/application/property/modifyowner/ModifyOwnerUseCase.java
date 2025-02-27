package com.monopoly.monopoly_managment.application.property.modifyowner;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import com.monopoly.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

public class ModifyOwnerUseCase implements ICommandUseCase<ModifyOwnerRequest, Mono<PropertyResponse>> {
  private final IEventsRepositoryPort repository;

  public ModifyOwnerUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PropertyResponse> execute(ModifyOwnerRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        events.sort(Comparator.comparing(DomainEvent::getWhen));
        Property property = Property.from(request.getAggregateId(), events);
        property.modifiedOwner(request.getOwnerId(), request.getPreviousOwnerId());
        property.getUncommittedEvents().forEach(repository::save);
        property.markEventsAsCommitted();
        return PropertyMapper.toPropertyResponse(property);
      });
  }
}
