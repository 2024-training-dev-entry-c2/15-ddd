package com.buildingclue.appointments.infra.mainservice.controllers.incidentcontroller;

import com.buildingclue.gameDynamics.application.incident.indetifysuspect.IdentifySuspectRequest;
import com.buildingclue.gameDynamics.application.incident.indetifysuspect.IdentifySuspectResponse;
import com.buildingclue.gameDynamics.application.incident.indetifysuspect.IdentifySuspectUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/incident")
public class IdentifySuspectController {

  private final IdentifySuspectUseCase identifySuspectUseCase;

  public IdentifySuspectController(IdentifySuspectUseCase identifySuspectUseCase) {
    this.identifySuspectUseCase = identifySuspectUseCase;
  }

  @PostMapping("/identify-suspect")
  public Mono<IdentifySuspectResponse> execute(@RequestBody IdentifySuspectRequest request) {
    return identifySuspectUseCase.execute(request);
  }
}