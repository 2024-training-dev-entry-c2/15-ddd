package com.monopoly.monopoly_managment.application.property.makemortgage;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.application.shared.repositories.IEventsRepository;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class MakeMortgageUseCase implements ICommandUseCase<MakeMortgageRequest, Mono<PropertyResponse>> {
  private final IEventsRepository repository;

  public MakeMortgageUseCase(IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PropertyResponse> execute(MakeMortgageRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        Property property = Property.from(request.getAggregateId(), events);
        property.mortgaged(request.getOwnerId(), request.getPropertyId(), request.getAmount());
        property.getUncommittedEvents().forEach(repository::save);
        property.markEventsAsCommitted();
        return PropertyMapper.toPropertyResponse(property);
      });
  }
}
