package com.monopoly.monopoly_managment.application.property.cancelmortgage;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import com.monopoly.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

public class CancelMortgageUseCase implements ICommandUseCase<CancelMortgageRequest, Mono<PropertyResponse>> {
  private final IEventsRepositoryPort repository;

  public CancelMortgageUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PropertyResponse> execute(CancelMortgageRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        events.sort(Comparator.comparing(DomainEvent::getWhen));
        Property property = Property.from(request.getAggregateId(), events);
        property.canceledMortgage(request.getOwnerId(), request.getPropertyId(), request.getAmount());
        property.getUncommittedEvents().forEach(repository::save);
        property.markEventsAsCommitted();
        return PropertyMapper.toPropertyResponse(property);
      });
  }
}
