package com.buildingclue.appointments.infra.mainservice.controllers.gamecontroller;

import com.buildingclue.gameDynamics.application.game.turn.EndTurnRequest;
import com.buildingclue.gameDynamics.application.game.turn.EndTurnResponse;
import com.buildingclue.gameDynamics.application.game.turn.EndTurnUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/game")
public class EndTurnController {

  private final EndTurnUseCase endTurnUseCase;

  public EndTurnController(EndTurnUseCase endTurnUseCase) {
    this.endTurnUseCase = endTurnUseCase;
  }

  @PostMapping("/end-turn")
  public Mono<EndTurnResponse> execute(@RequestBody EndTurnRequest request) {
    return endTurnUseCase.execute(request);
  }
}