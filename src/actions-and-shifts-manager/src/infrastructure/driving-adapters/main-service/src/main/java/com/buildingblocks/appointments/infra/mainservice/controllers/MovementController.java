package com.buildingblocks.appointments.infra.mainservice.controllers;

import com.buildingblocks.actionsandshiftsmanager.application.moveByCard.MovementRequest;
import com.buildingblocks.actionsandshiftsmanager.application.moveByCard.MovementResponse;
import com.buildingblocks.actionsandshiftsmanager.application.moveByCard.MovementUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movement")
public class MovementController {
    private final MovementUseCase useCase;

    public MovementController(MovementUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Mono<MovementResponse> execute(@RequestBody MovementRequest request){
        return useCase.execute(request);
    }
}
