package com.monopoly.monopoly_managment.infa.mongo.repositories;

import com.monopoly.monopoly_managment.infa.mongo.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IEventsRepository extends ReactiveMongoRepository<Event, String> {
}
