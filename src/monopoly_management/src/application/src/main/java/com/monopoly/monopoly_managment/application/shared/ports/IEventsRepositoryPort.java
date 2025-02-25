package com.monopoly.monopoly_managment.application.shared.ports;

import com.monopoly.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepositoryPort {
  Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
  Flux<DomainEvent> findAllAggregate();
  void save(DomainEvent domainEvent);
}
