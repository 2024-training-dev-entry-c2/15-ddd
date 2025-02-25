package com.buildingclue.appointments.infra.mainservice.controllers.gamecontroller;

import com.buildingclue.gameDynamics.application.game.startgame.StartGameRequest;
import com.buildingclue.gameDynamics.application.game.startgame.StartGameResponse;
import com.buildingclue.gameDynamics.application.game.startgame.StartGameUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/start-game")
public class StartGameController {
  private final StartGameUseCase startGameUseCase;

  public StartGameController(StartGameUseCase startGameUseCase) {
    this.startGameUseCase = startGameUseCase;
  }

  @PostMapping
  public Mono<StartGameResponse> execute(@RequestBody StartGameRequest request) {
    return startGameUseCase.execute(request);
  }
}
