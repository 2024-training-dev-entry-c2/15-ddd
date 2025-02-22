package com.monopoly.monopoly_managment.application.property.madeimprovement;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class MadeImprovementUseCase implements ICommandUseCase<MadeImprovementRequest, Mono<PropertyResponse>> {
  private final IEventsRepository repository;

  public MadeImprovementUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PropertyResponse> execute(MadeImprovementRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        Property property = Property.from(request.getAggregateId(), events);
        property.madeImprovement(request.getImprovementId(), request.getPropertyId(), request.getType(), request.getCost());
        property.getUncommittedEvents().forEach(repository::save);
        property.markEventsAsCommitted();
        return PropertyMapper.toPropertyResponse(property);
      });
  }
}
