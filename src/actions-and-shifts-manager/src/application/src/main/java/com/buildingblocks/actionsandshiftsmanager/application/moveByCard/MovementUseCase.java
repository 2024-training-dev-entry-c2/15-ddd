package com.buildingblocks.actionsandshiftsmanager.application.moveByCard;

import com.buildingblocks.actionsandshiftsmanager.application.shared.MethodPerformed;
import com.buildingblocks.actionsandshiftsmanager.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.Movement;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.PlayerPhase;
import com.buildingblocks.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class MovementUseCase implements ICommandUseCase<MovementRequest, Mono<MovementResponse>> {
    private final IEventsRepositoryPort repository;

    public MovementUseCase(IEventsRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<MovementResponse> execute(MovementRequest request) {
        Movement movement = new Movement();
        PlayerPhase playerPhase = new PlayerPhase();

        movement.modifiedMethodPerformed(request.getMethodPerformed().getId(), request.getMethodPerformed().getName(), request.getMethodPerformed().getDescription());
        movement.choosePlace(request.getTypePlace());

        playerPhase.cardDeleted(request.getCard().getId(), request.getCard().getName(), request.getCard().getType(), request.getCard().getAction());

        playerPhase.calculatedPoints(request.getPoints().getAmountPoints());

        playerPhase.playerChanged(request.getNextPlayerId(), request.getNextPlayerName());

        playerPhase.getUncommittedEvents().forEach(repository::save);
        playerPhase.markEventsAsCommitted();

        movement.getUncommittedEvents().forEach(repository::save);
        movement.markEventsAsCommitted();

        return Mono.just(
                new MovementResponse(
                        movement.getMethodPerformed().getName().getName(),
                        new MethodPerformed(
                                movement.getMethodPerformed().getIdentity().getValue(),
                                movement.getMethodPerformed().getName().getName(),
                                movement.getMethodPerformed().getDescription().getDescription()
                        ),
                        playerPhase.getAmountPoints().getAmountPoints()
                )
        );
    }
}
