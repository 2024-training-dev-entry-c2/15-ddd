package com.monopoly_managment.infa.mainservices.config;

import com.monopoly.monopoly_managment.infa.mongo.adapters.MongoAdapter;
import com.monopoly.monopoly_managment.infa.mongo.repositories.IEventsRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EntityScan(basePackages = "com.monopoly.monopoly_managment.infa.mongo.entities")
@EnableReactiveMongoRepositories(basePackages = "com.monopoly.monopoly_managment.infa.mongo.repositories")
public class MongoConfig {
  @Bean
  public MongoAdapter mongoAdapter(IEventsRepository repository) {
    return new MongoAdapter(repository);
  }

}
