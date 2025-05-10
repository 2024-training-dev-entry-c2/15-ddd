package com.buildingclue.appointments.infra.mainservice.controllers.gamecontroller;

import com.buildingclue.gameDynamics.application.game.turn.StartTurnRequest;
import com.buildingclue.gameDynamics.application.game.turn.StartTurnResponse;
import com.buildingclue.gameDynamics.application.game.turn.StartTurnUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/game")
public class StartTurnController {
  private final StartTurnUseCase startTurnUseCase;

  public StartTurnController(StartTurnUseCase startTurnUseCase) {
    this.startTurnUseCase = startTurnUseCase;
  }

  @PostMapping("/start-turn")
  public Mono<StartTurnResponse> execute(@RequestBody StartTurnRequest request) {
    return startTurnUseCase.execute(request);
  }
}