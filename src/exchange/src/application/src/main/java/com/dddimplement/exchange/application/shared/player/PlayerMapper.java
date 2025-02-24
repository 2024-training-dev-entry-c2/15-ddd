package com.dddimplement.exchange.application.shared.player;

import com.dddimplement.exchange.domain.player.Player;
import com.dddimplement.exchange.domain.player.entities.Offer;
import com.dddimplement.exchange.domain.player.entities.Territory;
import com.dddimplement.exchange.domain.player.entities.Turn;
import com.dddimplement.exchange.domain.player.values.ResourceType;

import java.util.stream.Collectors;

public class PlayerMapper {
    public static PlayerResponse mapToPlayer(Player player) {
        return new PlayerResponse(
                player.getIdentity().getValue(),
                player.getName().getValue(),
                player.getColor().getValue(),
                player.getOffer() != null ? mapOffer(player.getOfferById(player.getOffer().getIdentity().getValue())) : null,
                player.getTerritory() != null ? mapTerritory(player.getTerritoryById(player.getTerritory().getIdentity().getValue())) : null,
                player.getResources().stream().map(ResourceType::getType).map(Enum::name).collect(Collectors.toList()),
                player.getTurn() != null ? mapTurn(player.getTurnById(player.getTurn().getIdentity().getValue())) : null
        );
    }

    private static PlayerResponse.Offer mapOffer(Offer offer) {
        return new PlayerResponse.Offer(
                offer.getIdentity().getValue(),
                offer.getAmount().getValue(),
                offer.getType().getType().name(),
                offer.getIsAccepted().getValue()
        );
    }

    private static PlayerResponse.Territory mapTerritory(Territory territory) {
        return new PlayerResponse.Territory(
                territory.getIdentity().getValue(),
                territory.getCity().getValue(),
                territory.getPath().getValue(),
                territory.getSettlement().getValue()
        );
    }

    private static PlayerResponse.Turn mapTurn(Turn turn) {
        return new PlayerResponse.Turn(
                turn.getIdentity().getValue(),
                turn.getFase().getFase().name()
        );
    }
}