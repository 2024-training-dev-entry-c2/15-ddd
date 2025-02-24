package com.dddimplement.exchange.domain.player;

import com.dddimplement.exchange.domain.player.entities.Offer;
import com.dddimplement.exchange.domain.player.entities.Territory;
import com.dddimplement.exchange.domain.player.entities.Turn;
import com.dddimplement.exchange.domain.player.events.*;
import com.dddimplement.exchange.domain.player.values.*;
import com.dddimplement.shared.domain.generic.DomainActionsContainer;
import com.dddimplement.shared.domain.generic.DomainEvent;

import java.util.List;
import java.util.function.Consumer;

public class Playerhandler extends DomainActionsContainer {

    public Playerhandler(Player player) {
        add(createPlayer(player));
        add(OfferCreated(player));
        add(OfferAccepted(player));
        add(OfferRejected(player));
        add(CounterOfferCreated(player));
        add(TerritoryCreated(player));
        add(TerritoryExpanded(player));
        add(TerritoryImproved(player));
        add(TurnStarted(player));
        add(TurnEnded(player));
    }

    public Consumer<? extends DomainEvent> createPlayer(Player player) {
        return  (CreatedPlayer event) ->{
            player.setName(Name.of(event.getName()));
            player.setColor(Color.of(event.getColor()));

            Territory initialTerritory = new Territory(
                    City.of(0),
                    Path.of(2),
                    Settlement.of(2)
            );
            player.setTerritory(initialTerritory);

            ResourceType brickResource = ResourceType.of(ResourceTypeEnum.BRICK, Amount.of(2));
            ResourceType sheepResource = ResourceType.of(ResourceTypeEnum.SHEEP, Amount.of(2));
            ResourceType stoneResource = ResourceType.of(ResourceTypeEnum.STONE, Amount.of(2));
            ResourceType woodResource = ResourceType.of(ResourceTypeEnum.WOOD, Amount.of(2));
            ResourceType wheatResource = ResourceType.of(ResourceTypeEnum.WHEAT, Amount.of(2));


            player.setResources(List.of(brickResource, sheepResource, stoneResource, woodResource, wheatResource));
            Turn initialTurn = new Turn(TurnFase.of(TurnFaseEnum.ROLL));
            player.setTurn(initialTurn);
        };
    }

    // region Offer

    public Consumer<? extends DomainEvent> OfferCreated(Player player){
        return (OfferCreated event) -> {
            ResourceTypeEnum resourceType = ResourceTypeEnum.valueOf(event.getResourceType());
            Offer offer = new Offer(Amount.of(event.getAmount()), ResourceType.of(resourceType, Amount.of(event.getAmount())), IsAccepted.of(false));
            player.setOffer(offer);
        };
    }

    public Consumer<? extends DomainEvent> OfferAccepted(Player player) {
        return (OfferAccepted event) -> {
            Offer offer = player.getOffer();
                offer.accept();
        };
    }

    public Consumer<? extends DomainEvent> OfferRejected(Player player) {
        return (OfferRejected event) -> {
            Offer offer = player.getOffer();
            if (offer != null) {
                offer.reject();
            } else {
                throw new IllegalStateException("Offer is null");
            }
        };
    }

    public Consumer<? extends DomainEvent> CounterOfferCreated(Player player) {
        return (CounterOfferCreated event) -> {
            try {
                ResourceTypeEnum resourceType = ResourceTypeEnum.valueOf(event.getResourceType());
                Amount amount = Amount.of(event.getAmount());
                Offer offer = new Offer(Amount.of(event.getAmount()), ResourceType.of(resourceType, amount), IsAccepted.of(false));
                player.setOffer(offer);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid resource type: " + event.getResourceType(), e);
            }
        };
    }

    // endregion

    //region Territory
    public Consumer<? extends DomainEvent> TerritoryCreated(Player player) {
        return (TerritoryCreated event) -> {
            player.setTerritory(new Territory(City.of(0), Path.of(0), Settlement.of(0)));
        };
    }

    public Consumer<? extends DomainEvent> TerritoryExpanded(Player player) {
        return (TerritoryExpanded event) -> {
            Territory territory = player.getTerritory();
            territory.expand();
        };
    }

    public Consumer<TerritoryImproved> TerritoryImproved(Player player) {
        return (TerritoryImproved event) -> {
            Territory currentTerritory = player.getTerritory();

            if (event.getTerritoryType().equals("CITY")) {
                player.setTerritory(new Territory(
                        City.of(currentTerritory.getCity().getValue() + 1),
                        currentTerritory.getPath(),
                        Settlement.of(currentTerritory.getSettlement().getValue() - 1) // Restar asentamiento porque se convirtió en ciudad
                ));
            }
        };
    }

    //endregion

    //region Turn
    public Consumer<? extends DomainEvent> TurnStarted(Player player) {
        return (TurnStarted event) -> {
            Turn turn = player.getTurn();
            turn.setFase(TurnFase.of(event.getTurnFase()));
            turn.rollDice();
        };
    }

    public Consumer<? extends DomainEvent> TurnEnded(Player player) {
        return (TurnEnded event) -> {
            Turn turn = player.getTurn();
            turn.nextFase();
        };
    }
    //endregion
}
