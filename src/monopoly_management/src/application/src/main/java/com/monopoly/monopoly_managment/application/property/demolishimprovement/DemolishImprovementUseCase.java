package com.monopoly.monopoly_managment.application.property.demolishimprovement;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import com.monopoly.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

public class DemolishImprovementUseCase implements ICommandUseCase<DemolishImprovementRequest, Mono<PropertyResponse>> {
  private final IEventsRepositoryPort repository;

  public DemolishImprovementUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PropertyResponse> execute(DemolishImprovementRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        events.sort(Comparator.comparing(DomainEvent::getWhen));
        Property property = Property.from(request.getAggregateId(), events);
        property.demolishedImprovement(request.getImprovementId(), request.getPropertyId(), request.getType(), request.getCost());
        property.getUncommittedEvents().forEach(repository::save);
        property.markEventsAsCommitted();
        return PropertyMapper.toPropertyResponse(property);
      });
  }
}
