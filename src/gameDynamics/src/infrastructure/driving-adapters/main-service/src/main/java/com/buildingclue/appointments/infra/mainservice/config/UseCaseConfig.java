package com.buildingclue.appointments.infra.mainservice.config;

import com.buildingclue.gameDynamics.application.game.accusation.AccusationUseCase;
import com.buildingclue.gameDynamics.application.game.endgame.EndGameUseCase;
import com.buildingclue.gameDynamics.application.game.move.MoveUseCase;
import com.buildingclue.gameDynamics.application.game.startgame.StartGameUseCase;
import com.buildingclue.gameDynamics.application.game.turn.EndTurnUseCase;
import com.buildingclue.gameDynamics.application.game.turn.StartTurnUseCase;
import com.buildingclue.gameDynamics.application.incident.addclue.AddClueUseCase;
import com.buildingclue.gameDynamics.application.incident.determinelocation.DetermineLocationUseCase;
import com.buildingclue.gameDynamics.application.incident.identifyweapon.IdentifyWeaponUseCase;
import com.buildingclue.gameDynamics.application.incident.indetifysuspect.IdentifySuspectUseCase;
import com.buildingclue.gameDynamics.application.incident.solveincident.SolveIncidentUseCase;
import com.buildingclue.appointments.infra.mongo.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  //Start Game
  @Bean
  public StartGameUseCase startGameUseCase(MongoAdapter adapter){
    return new StartGameUseCase(adapter);
  }

  @Bean
  public StartTurnUseCase startTurnUseCase(MongoAdapter adapter){
    return new StartTurnUseCase(adapter);
  }

  @Bean
  public MoveUseCase moveUseCase(MongoAdapter adapter){
    return new MoveUseCase(adapter);
  }

  @Bean
  public AccusationUseCase accusationUseCase(MongoAdapter adapter){
    return new AccusationUseCase(adapter);
  }

  @Bean
  public EndTurnUseCase endTurnUseCase(MongoAdapter adapter){
    return new EndTurnUseCase(adapter);
  }

  @Bean
  public EndGameUseCase endGameUseCase(MongoAdapter adapter){
    return new EndGameUseCase(adapter);
  }

  //End Game

  //Start Incident
  @Bean
  public AddClueUseCase addClueUseCase(MongoAdapter adapter){
    return new AddClueUseCase(adapter);
  }

  @Bean
  public DetermineLocationUseCase determineLocationUseCase(MongoAdapter adapter){
    return new DetermineLocationUseCase(adapter);
  }

  @Bean
  public IdentifyWeaponUseCase identifyWeaponUseCase(MongoAdapter adapter){
    return new IdentifyWeaponUseCase(adapter);
  }

  @Bean
  public IdentifySuspectUseCase identifySuspectUseCase(MongoAdapter adapter){
    return new IdentifySuspectUseCase(adapter);
  }

  @Bean
  public SolveIncidentUseCase solveIncidentUseCase(MongoAdapter adapter){
    return new SolveIncidentUseCase(adapter);
  }

  //End Incident
}
