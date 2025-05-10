package com.buildingclue.appointments.infra.mainservice.controllers.incidentcontroller;

import com.buildingclue.gameDynamics.application.incident.solveincident.SolveIncidentRequest;
import com.buildingclue.gameDynamics.application.incident.solveincident.SolveIncidentResponse;
import com.buildingclue.gameDynamics.application.incident.solveincident.SolveIncidentUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/incident")
public class SolveIncidentController {

  private final SolveIncidentUseCase solveIncidentUseCase;

  public SolveIncidentController(SolveIncidentUseCase solveIncidentUseCase) {
    this.solveIncidentUseCase = solveIncidentUseCase;
  }

  @PostMapping("/solve-incident")
  public Mono<SolveIncidentResponse> execute(@RequestBody SolveIncidentRequest request) {
    return solveIncidentUseCase.execute(request);
  }
}