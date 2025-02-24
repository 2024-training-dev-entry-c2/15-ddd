package com.dddimplement.exchange.domain.player;

import com.dddimplement.exchange.domain.player.entities.Offer;
import com.dddimplement.exchange.domain.player.entities.Territory;
import com.dddimplement.exchange.domain.player.entities.Turn;
import com.dddimplement.exchange.domain.player.events.*;
import com.dddimplement.exchange.domain.player.values.*;
import com.dddimplement.shared.domain.generic.AggregateRoot;
import com.dddimplement.shared.domain.generic.DomainEvent;

import java.util.List;

public class Player extends AggregateRoot<PlayerId> {
    private Name name;
    private Color color;
    private Offer offer;
    private Territory territory;
    private List<ResourceType> resources;
    private Turn turn;

    // region Constructors

    private Player(PlayerId identity) {
        super(identity);
        subscribe(new Playerhandler(this));
    }

    public Player(String name, String color) {
        super(new PlayerId());
        subscribe(new Playerhandler(this));
        apply(new CreatedPlayer(name, color));
    }

    public static Player from(final String identity, final List<DomainEvent> events) {
        Player player = new Player(PlayerId.of(identity));
        events.forEach(player::apply);
        return player;
    }
    // endregion

    // region Getter and Setter
    public Name getName() {
        return name;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public List<ResourceType> getResources() {
        return resources;
    }

    public void setResources(List<ResourceType> resources) {
        this.resources = resources;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
    // endregion

    // region Doman Actions
    public void createdOffer(Integer amount, String type) {
        apply(new OfferCreated(amount, type));
    }

    public void createdPlayer(String name, String color) {
        apply(new CreatedPlayer(name, color));
    }

    public void aceptedOffer(String id) {
        apply(new OfferAccepted(id));
    }

    public void rejectedOffer(String id) {
        apply(new OfferRejected(id));
    }

    public void createdCounterOffer(Integer amount, String type) {
        apply(new CounterOfferCreated(amount, type));
    }

    public void createdTerritory(String typeTerritory) {
        apply(new TerritoryCreated(typeTerritory));
    }

    public void expandedTerriorty(String typeTerritory) {
        apply(new TerritoryExpanded(typeTerritory));
    }

    public void improvedTerritory(String typeTerritory) {
        apply(new TerritoryImproved(typeTerritory));
    }

    public void startedTurn(TurnFaseEnum turnFase) {
        apply(new TurnStarted(turnFase));
    }

    public void endedTurn(TurnFaseEnum turnFase) {
        apply(new TurnEnded(turnFase));
    }
    // endregion

    // region public methods
    public Territory getTerritoryById(final String territoryId) {
        return new Territory(TerritoryId.of(territoryId), this.territory.getCity(), this.territory.getPath(), this.territory.getSettlement());
    }

    public Offer getOfferById(final String offerId) {
        return new Offer(OfferId.of(offerId), this.offer.getAmount(), this.offer.getType(), this.offer.getIsAccepted());
    }

    public Turn getTurnById(final String turnId) {
        return new Turn(TurnId.of(turnId), this.turn.getFase());
    }
    // endregion
}