package com.monopoly.monopoly_managment.infa.mongo.adapters;

import com.monopoly.monopoly_managment.application.shared.ports.IEventsRepositoryPort;
import com.monopoly.shared.domain.generic.DomainEvent;
import com.monopoly.monopoly_managment.infa.mongo.entities.Event;
import com.monopoly.monopoly_managment.infa.mongo.repositories.IEventsRepository;
import reactor.core.publisher.Flux;

public class MongoAdapter implements IEventsRepositoryPort {
  private final IEventsRepository eventsRepository;

  public MongoAdapter(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Flux<DomainEvent> findEventsByAggregateId(String aggregateId) {
    return findAllAggregate().filter(e -> e.getAggregateRootId().equals(aggregateId));
  }

  @Override
  public Flux<DomainEvent> findAllAggregate() {
    return eventsRepository.findAll().map(Event::getDomainEvent);
  }

  @Override
  public void save(DomainEvent domainEvent) {
eventsRepository.save(new Event(domainEvent)).subscribe();
  }
}
