package com.buildingclue.appointments.infra.mainservice.controllers.incidentcontroller;

import com.buildingclue.gameDynamics.application.incident.identifyweapon.IdentifyWeaponRequest;
import com.buildingclue.gameDynamics.application.incident.identifyweapon.IdentifyWeaponResponse;
import com.buildingclue.gameDynamics.application.incident.identifyweapon.IdentifyWeaponUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/incident")
public class IdentifyWeaponController {

  private final IdentifyWeaponUseCase identifyWeaponUseCase;

  public IdentifyWeaponController(IdentifyWeaponUseCase identifyWeaponUseCase) {
    this.identifyWeaponUseCase = identifyWeaponUseCase;
  }

  @PostMapping("/identify-weapon")
  public Mono<IdentifyWeaponResponse> execute(@RequestBody IdentifyWeaponRequest request) {
    return identifyWeaponUseCase.execute(request);
  }
}