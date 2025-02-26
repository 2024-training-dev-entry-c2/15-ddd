package com.buildingclue.appointments.infra.mainservice.controllers.gamecontroller;

import com.buildingclue.gameDynamics.application.game.endgame.EndGameRequest;
import com.buildingclue.gameDynamics.application.game.endgame.EndGameResponse;
import com.buildingclue.gameDynamics.application.game.endgame.EndGameUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/game")
public class EndGameController {

  private final EndGameUseCase endGameUseCase;

  public EndGameController(EndGameUseCase endGameUseCase) {
    this.endGameUseCase = endGameUseCase;
  }

  @PostMapping("/end-game")
  public Mono<EndGameResponse> execute(@RequestBody EndGameRequest request) {
    return endGameUseCase.execute(request);
  }
}