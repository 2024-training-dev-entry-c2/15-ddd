package com.buildingblocks.actionsandshiftsmanager.domain.playerphase;

import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.entities.Card;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.entities.Player;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events.*;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.Action;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.AmountPoints;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.Name;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.Type;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;

public class PlayerPhaseHandler extends DomainActionsContainer {
    public PlayerPhaseHandler(PlayerPhase playerPhase) {
        add((AssignedPlayer event) -> {
            Player player = new Player(Name.of(event.getName()));
            playerPhase.setPlayer(player);
        });
        add((AssignedCard event) -> {
            Card card = new Card(Name.of(event.getName()), Type.of(event.getType()), Action.of(event.getEffect()));
            playerPhase.getCardList().add(card);
        });
        add((CalculatedPoints event) -> {
            playerPhase.setAmountPoints(AmountPoints.of(event.getAmountPoints()));
        });
        add((CardDeleted event) -> {
            playerPhase.getCardList().removeIf(card1 -> card1.getName().getName().equals(event.getName()));
        });
        add((ModifiedPoints event) -> {
            playerPhase.setAmountPoints(AmountPoints.of(event.getAmountPoints()));
        });
        add((PlayerChanged event) -> {
            Player player = new Player(Name.of(event.getName()));
            playerPhase.setPlayer(player);
        });
    }
}
