package com.buildingclue.appointments.infra.mainservice.controllers.incidentcontroller;

import com.buildingclue.gameDynamics.application.incident.addclue.AddClueRequest;
import com.buildingclue.gameDynamics.application.incident.addclue.AddClueResponse;
import com.buildingclue.gameDynamics.application.incident.addclue.AddClueUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/incident")
public class AddClueController {
  private final AddClueUseCase addClueUseCase;

  public AddClueController(AddClueUseCase addClueUseCase) {
    this.addClueUseCase = addClueUseCase;
  }

  @PostMapping("/add-clue")
  public Mono<AddClueResponse> execute(@RequestBody AddClueRequest request) {
    return addClueUseCase.execute(request);
  }
}
