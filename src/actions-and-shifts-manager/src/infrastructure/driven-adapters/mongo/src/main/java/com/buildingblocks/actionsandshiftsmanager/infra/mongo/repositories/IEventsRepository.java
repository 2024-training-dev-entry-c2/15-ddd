package com.buildingblocks.actionsandshiftsmanager.infra.mongo.repositories;

import com.buildingblocks.actionsandshiftsmanager.infra.mongo.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IEventsRepository extends ReactiveMongoRepository<Event, String> {
}
