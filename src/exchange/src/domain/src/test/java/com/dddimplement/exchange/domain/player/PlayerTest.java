package com.dddimplement.exchange.domain.player;

import com.dddimplement.exchange.domain.player.entities.Offer;
import com.dddimplement.exchange.domain.player.entities.Territory;
import com.dddimplement.exchange.domain.player.entities.Turn;
import com.dddimplement.exchange.domain.player.events.CreatedPlayer;
import com.dddimplement.exchange.domain.player.values.*;
import com.dddimplement.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("John", "Red");
    }

    @Test
    void testPlayerCreation() {
        assertEquals("John", player.getName().getValue());
        assertEquals("Red", player.getColor().getValue());

        Territory territory = player.getTerritory();
        assertNotNull(territory);
        assertEquals(0, territory.getCity().getValue());
        assertEquals(2, territory.getPath().getValue());
        assertEquals(2, territory.getSettlement().getValue());

        List<ResourceType> resources = player.getResources();
        assertNotNull(resources);
        assertEquals(5, resources.size());
        assertTrue(resources.stream().anyMatch(resource -> resource.getType() == ResourceTypeEnum.BRICK && resource.getAmount().getValue() == 2));
        assertTrue(resources.stream().anyMatch(resource -> resource.getType() == ResourceTypeEnum.SHEEP && resource.getAmount().getValue() == 2));
        assertTrue(resources.stream().anyMatch(resource -> resource.getType() == ResourceTypeEnum.STONE && resource.getAmount().getValue() == 2));
        assertTrue(resources.stream().anyMatch(resource -> resource.getType() == ResourceTypeEnum.WOOD && resource.getAmount().getValue() == 2));
        assertTrue(resources.stream().anyMatch(resource -> resource.getType() == ResourceTypeEnum.WHEAT && resource.getAmount().getValue() == 2));
    }

    @Test
    void testOfferCreation() {
        player.createdOffer(3, "BRICK");
        Offer offer = player.getOffer();
        assertNotNull(offer);
        assertEquals(3, offer.getAmount().getValue());
        assertEquals(ResourceTypeEnum.BRICK, offer.getType().getType());
        assertFalse(offer.getIsAccepted().getValue());
    }

    @Test
    void testOfferAcceptance() {
        player.createdOffer(3, "BRICK");
        player.aceptedOffer("1");

        Offer offer = player.getOffer();
        assertNotNull(offer, "The offer should not be null");
        assertTrue(offer.getIsAccepted().getValue(), "The offer was not accepted properly");
    }

    @Test
    void testOfferRejection() {
        player.createdOffer(3, "BRICK");
        player.rejectedOffer("1");
        Offer offer = player.getOffer();
        assertNotNull(offer);
        assertFalse(offer.getIsAccepted().getValue());
    }

    @Test
    void testCounterOfferCreation() {
        player.createdCounterOffer(4, "STONE");
        Offer offer = player.getOffer();
        assertNotNull(offer);
        assertEquals(4, offer.getAmount().getValue());
        assertEquals(ResourceTypeEnum.STONE, offer.getType().getType());
        assertFalse(offer.getIsAccepted().getValue());
    }

    @Test
    void testTerritoryCreation() {
        player.createdTerritory("CITY");
        Territory territory = player.getTerritory();
        assertNotNull(territory);
        assertEquals(0, territory.getCity().getValue());
        assertEquals(0, territory.getPath().getValue());
        assertEquals(0, territory.getSettlement().getValue());
    }

    @Test
    void testTerritoryExpansion() {
        player.createdTerritory("CITY");
        player.expandedTerriorty("CITY");
        Territory territory = player.getTerritory();
        assertNotNull(territory);
        assertEquals(1, territory.getPath().getValue());
    }

    @Test
    void testTurnStart() {
        player.startedTurn(TurnFaseEnum.ROLL);
        Turn turn = player.getTurn();
        assertNotNull(turn);
        assertEquals(TurnFaseEnum.ROLL, turn.getFase().getFase());
    }

    @Test
    void testTurnEnd() {
        player.startedTurn(TurnFaseEnum.ROLL);
        player.endedTurn(TurnFaseEnum.ROLL);
        Turn turn = player.getTurn();
        assertNotNull(turn);
        assertEquals(TurnFaseEnum.TRADE, turn.getFase().getFase());
    }

    @Test
    void testImprovedTerritory() {
        player.createdTerritory("CITY");
        player.getTerritory().setSettlement(Settlement.of(1)); // Aseguramos que haya al menos un asentamiento para mejorar
        player.improvedTerritory("CITY");
        Territory territory = player.getTerritory();
        assertNotNull(territory);
        assertEquals(1, territory.getCity().getValue());
        assertEquals(0, territory.getSettlement().getValue()); // El asentamiento debe ser 0 después de la mejora
    }

    @Test
    void testStartedTurn() {
        player.startedTurn(TurnFaseEnum.ROLL);
        Turn turn = player.getTurn();
        assertNotNull(turn);
        assertEquals(TurnFaseEnum.ROLL, turn.getFase().getFase());
    }

    @Test
    void testEndedTurn() {
        player.startedTurn(TurnFaseEnum.ROLL);
        player.endedTurn(TurnFaseEnum.ROLL);
        Turn turn = player.getTurn();
        assertNotNull(turn);
        assertEquals(TurnFaseEnum.TRADE, turn.getFase().getFase());
    }

    @Test
    void testCreatedPlayer() {
        Player newPlayer = new Player("Alice", "Blue");
        newPlayer.createdPlayer("Alice", "Blue");
        assertEquals("Alice", newPlayer.getName().getValue());
        assertEquals("Blue", newPlayer.getColor().getValue());
    }

    @Test
    void fromMethod() {
        String identity = "player-1";
        List<DomainEvent> events = List.of(new CreatedPlayer("PlayerOne", "red"));

        Player player = Player.from(identity, events);

        assertNotNull(player);
        assertEquals(identity, player.getIdentity().getValue());
        assertEquals("PlayerOne", player.getName().getValue());
    }

    @Test
    void testTerritoryImproved() {
        player.createdTerritory("CITY");
        player.getTerritory().setSettlement(Settlement.of(1)); // Aseguramos que haya al menos un asentamiento para mejorar
        player.improvedTerritory("CITY");

        Territory territory = player.getTerritory();
        assertNotNull(territory);
        assertEquals(1, territory.getCity().getValue());
        assertEquals(0, territory.getSettlement().getValue()); // El asentamiento debe ser 0 después de la mejora
    }

    @Test
    void testTerritoryImprovedNonCity() {
        player.createdTerritory("SETTLEMENT");
        player.getTerritory().setSettlement(Settlement.of(1)); // Aseguramos que haya al menos un asentamiento para mejorar
        player.improvedTerritory("SETTLEMENT");

        Territory territory = player.getTerritory();
        assertNotNull(territory);
        assertEquals(0, territory.getCity().getValue()); // La ciudad no debe cambiar
        assertEquals(1, territory.getSettlement().getValue()); // El asentamiento debe permanecer igual
    }
}