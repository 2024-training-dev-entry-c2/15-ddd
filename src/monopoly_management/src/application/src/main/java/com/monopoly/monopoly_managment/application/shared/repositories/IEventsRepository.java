package com.monopoly.monopoly_managment.application.shared.repositories;

import com.monopoly.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepository {
  Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
  void save(DomainEvent domainEvent);
}
