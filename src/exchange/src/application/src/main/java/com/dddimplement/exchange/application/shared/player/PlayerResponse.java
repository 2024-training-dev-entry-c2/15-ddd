package com.dddimplement.exchange.application.shared.player;

import com.dddimplement.exchange.domain.player.entities.Territory;

import java.util.List;

public class PlayerResponse {
    private final String playerId;
    private final String name;
    private final String color;
    private final Offer offer;
    private final Territory territory;
    private final List<String> resources; // List<Resourc>
    private final Turn Turn;

    public PlayerResponse( String playerId, String name, String color, Offer offer, Territory territory, List<String> resources, Turn Turn) {
        this.playerId = playerId;
        this.name = name;
        this.color = color;
        this.offer = offer;
        this.territory = territory;
        this.resources = resources;
        this.Turn = Turn;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Offer getOffer() {
        return offer;
    }

    public Territory getTerritory() {
        return territory;
    }

    public Turn getTurn() {
        return Turn;
    }

    public List<String> getResources() {
        return resources;
    }



    public static class Territory{
        private final String id;
        private final Integer city;
        private final Integer path;
        private final Integer settlement;

        public Territory(String id, Integer city, Integer path, Integer settlement) {
            this.id = id;
            this.city = city;
            this.path = path;
            this.settlement = settlement;
        }

        public Integer getCity() {
            return city;
        }

        public Integer getPath() {
            return path;
        }

        public Integer getSettlement() {
            return settlement;
        }

        public String getId() {
            return id;
        }
    }

    public static class Turn{
        private final String id;
        private final String status;

        public Turn(String id, String status) {
            this.id = id;

            this.status = status;
        }

        public String getId() {
            return id;
        }


        public String getStatus() {
            return status;
        }
    }

    public static class Offer{
        private final String id;
        private final Integer amount;
        private final String type;
        private final Boolean isAccepted;

        public Offer(String id, Integer amount, String type, Boolean isAccepted) {
            this.id = id;
            this.amount = amount;
            this.type = type;
            this.isAccepted = isAccepted;
        }

        public String getId() {
            return id;
        }

        public Integer getAmount() {
            return amount;
        }

        public String getType() {
            return type;
        }

        public Boolean getIsAccepted() {
            return isAccepted;
        }
    }

    //region
}
