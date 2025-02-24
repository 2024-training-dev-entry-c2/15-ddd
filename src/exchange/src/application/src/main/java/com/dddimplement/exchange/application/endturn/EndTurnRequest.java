package com.dddimplement.exchange.application.endturn;

import com.buildingblocks.shared.application.Request;

public class EndTurnRequest extends Request {
    private String playerId;

    public EndTurnRequest(String aggregateId,String playerId) {
        super(aggregateId);
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

}