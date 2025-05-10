package com.buildingblocks.appointments.infra.mainservice.config;

import com.buildingblocks.actionsandshiftsmanager.application.moveByCard.MovementUseCase;
import com.buildingblocks.actionsandshiftsmanager.application.turnStart.CreateTurnUseCase;
import com.buildingblocks.actionsandshiftsmanager.infra.mongo.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public MovementUseCase movementUseCase(MongoAdapter adapter){
        return new MovementUseCase(adapter);
    }

    @Bean
    public CreateTurnUseCase createTurnUseCase(MongoAdapter adapter){
        return new CreateTurnUseCase(adapter);
    }
}
