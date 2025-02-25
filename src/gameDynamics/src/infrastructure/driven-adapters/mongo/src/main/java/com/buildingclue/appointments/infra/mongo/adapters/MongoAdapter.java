package com.buildingclue.appointments.infra.mongo.adapters;

import com.buildingclue.gameDynamics.application.shared.ports.IEventsRepositoryPort;
import com.buildingclue.appointments.infra.mongo.entities.Event;
import com.buildingclue.appointments.infra.mongo.repositories.IEventsRepository;
import com.buildingclue.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public class MongoAdapter implements IEventsRepositoryPort {

  private final IEventsRepository eventsRepository;

  public MongoAdapter(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Flux<DomainEvent> findAllAggregates() {
    return eventsRepository.findAll().map(Event::getDomainEvent);
  }

  @Override
  public Flux<DomainEvent> findEventsByAggregateId(String aggregateId) {
    return findAllAggregates().filter(event -> event.getAggregateRootId().equals(aggregateId));
  }

  @Override
  public void save(DomainEvent event) {
    eventsRepository.save(new Event(event)).subscribe();
  }
}
