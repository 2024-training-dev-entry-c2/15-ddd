package com.buildingclue.appointments.infra.mainservice.controllers.gamecontroller;

import com.buildingclue.gameDynamics.application.game.accusation.AccusationRequest;
import com.buildingclue.gameDynamics.application.game.accusation.AccusationResponse;
import com.buildingclue.gameDynamics.application.game.accusation.AccusationUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/game")
public class AccusationController {
  private final AccusationUseCase accusationUseCase;

  public AccusationController(AccusationUseCase accusationUseCase) {
    this.accusationUseCase = accusationUseCase;
  }

  @PostMapping("/accusation")
  public Mono<AccusationResponse> execute(@RequestBody AccusationRequest request) {
    return accusationUseCase.execute(request);
  }
}

