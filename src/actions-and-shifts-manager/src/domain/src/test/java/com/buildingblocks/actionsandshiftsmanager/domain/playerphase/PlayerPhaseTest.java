package com.buildingblocks.actionsandshiftsmanager.domain.playerphase;

import static org.junit.jupiter.api.Assertions.*;

import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events.AssignedCard;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events.AssignedPlayer;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events.CalculatedPoints;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events.ModifiedPoints;
import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events.PlayerChanged;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlayerPhaseTest {
    private PlayerPhase playerPhase;

    @BeforeEach
    void setUp() {
        playerPhase = new PlayerPhase();
    }

    @Test
    void assignedCardSuccess() {
        String name = "chicago";
        String type = "city";
        String effect = "discard to move this city";

        playerPhase.assignedCard(name, type, effect);
        assertEquals(1, playerPhase.getCardList().size());

        assertEquals(name, playerPhase.getCardList().getFirst().getName().getName());
        assertEquals(type, playerPhase.getCardList().getFirst().getType().getType());
        assertEquals(effect, playerPhase.getCardList().getFirst().getAction().getAction());
        assertInstanceOf(AssignedCard.class, playerPhase.getUncommittedEvents().get(0));
    }

    @Test
    void assignedPlayerSuccess() {
        playerPhase.assignedPlayer("Luca");
        assertEquals("Luca", playerPhase.getPlayer().getName().getName());
        assertInstanceOf(AssignedPlayer.class, playerPhase.getUncommittedEvents().get(0));
    }

    @Test
    void calculatedPoints() {
        playerPhase.calculatedPoints(3);
        assertEquals(3, playerPhase.getAmountPoints().getAmountPoints());
        assertInstanceOf(CalculatedPoints.class, playerPhase.getUncommittedEvents().get(0));
    }

    @Test
    void cardDeletedSuccess() {
        String id = "1";
        String name = "dicard card";
        String type = "city";
        String effect = "discard this card to go to the mentioned city";

        playerPhase.assignedCard(name, type, effect);
        assertEquals(1, playerPhase.getCardList().size());
        playerPhase.cardDeleted(id, name, type, effect);

        assertEquals(0, playerPhase.getCardList().size());
    }

    @Test
    void modifiedPointsSuccess(){
        playerPhase.modifiedPoints("1",3);
        assertEquals(3, playerPhase.getAmountPoints().getAmountPoints());
        assertInstanceOf(ModifiedPoints.class, playerPhase.getUncommittedEvents().get(0));
    }

    @Test
    void playerChangedSuccess(){
        playerPhase.playerChanged("1","Pablo");
        assertEquals("Pablo", playerPhase.getPlayer().getName().getName());
        assertInstanceOf(PlayerChanged.class, playerPhase.getUncommittedEvents().get(0));
    }

    @Test
    void PlayerPhaseRebuildAggregateSuccess() {
        playerPhase = PlayerPhase.from("1", List.of(new AssignedPlayer("juan")));
        assertEquals("1", playerPhase.getIdentity().getValue());
    }

}