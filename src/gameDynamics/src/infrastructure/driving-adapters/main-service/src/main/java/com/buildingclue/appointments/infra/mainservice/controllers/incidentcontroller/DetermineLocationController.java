package com.buildingclue.appointments.infra.mainservice.controllers.incidentcontroller;

import com.buildingclue.gameDynamics.application.incident.determinelocation.DetermineLocationRequest;
import com.buildingclue.gameDynamics.application.incident.determinelocation.DetermineLocationResponse;
import com.buildingclue.gameDynamics.application.incident.determinelocation.DetermineLocationUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/incident")
public class DetermineLocationController {

  private final DetermineLocationUseCase determineLocationUseCase;

  public DetermineLocationController(DetermineLocationUseCase determineLocationUseCase) {
    this.determineLocationUseCase = determineLocationUseCase;
  }

  @PostMapping("/determine-location")
  public Mono<DetermineLocationResponse> execute(@RequestBody DetermineLocationRequest request) {
    return determineLocationUseCase.execute(request);
  }
}
