package com.dddimplement.exchange.application.shared.repositories;

import com.dddimplement.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEventsRepository {
    Flux<DomainEvent> findAllAggregates();
    Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
    void save(DomainEvent domainEvent);
}
