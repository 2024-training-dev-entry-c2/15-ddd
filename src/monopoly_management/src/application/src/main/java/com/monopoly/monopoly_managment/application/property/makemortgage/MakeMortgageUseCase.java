package com.monopoly.monopoly_managment.application.property.makemortgage;

import com.monopoly.monopoly_managment.application.property.shared.PropertyMapper;
import com.monopoly.monopoly_managment.application.property.shared.PropertyResponse;
import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.monopoly_managment.domain.property.Property;
import com.monopoly.shared.application.ICommandUseCase;
import com.monopoly.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;

public class MakeMortgageUseCase implements ICommandUseCase<MakeMortgageRequest, Mono<PropertyResponse>> {
  private final IEventsRepositoryPort repository;

  public MakeMortgageUseCase(IEventsRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Mono<PropertyResponse> execute(MakeMortgageRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events ->{
        events.sort(Comparator.comparing(DomainEvent::getWhen));
        Property property = Property.from(request.getAggregateId(), events);
        property.mortgaged(request.getMortgageId() ,request.getOwnerId(), request.getAmount(), true);
        property.getUncommittedEvents().forEach(repository::save);
        property.markEventsAsCommitted();
        return PropertyMapper.toPropertyResponse(property);
      });
  }
}
