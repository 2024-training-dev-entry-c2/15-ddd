package com.buildingblocks.appointments.infra.mainservice.controllers;

import com.buildingblocks.actionsandshiftsmanager.application.turnStart.CreateTurnRequest;
import com.buildingblocks.actionsandshiftsmanager.application.turnStart.CreateTurnResponse;
import com.buildingblocks.actionsandshiftsmanager.application.turnStart.CreateTurnUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/create-turn")
public class CreateTurnController {
    private final CreateTurnUseCase useCase;

    public CreateTurnController(CreateTurnUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Mono<CreateTurnResponse> execute(@RequestBody CreateTurnRequest request){
        return useCase.execute(request);
    }
}
