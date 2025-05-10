package com.buildingclue.appointments.infra.mainservice.controllers.gamecontroller;

import com.buildingclue.gameDynamics.application.game.move.MoveRequest;
import com.buildingclue.gameDynamics.application.game.move.MoveResponse;
import com.buildingclue.gameDynamics.application.game.move.MoveUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/game")
public class MoveController {
  private final MoveUseCase moveUseCase;

  public MoveController(MoveUseCase moveUseCase) {
    this.moveUseCase = moveUseCase;
  }

  @PostMapping("/move")
  public Mono<MoveResponse> execute(@RequestBody MoveRequest request) {
    return moveUseCase.execute(request);
  }
}
