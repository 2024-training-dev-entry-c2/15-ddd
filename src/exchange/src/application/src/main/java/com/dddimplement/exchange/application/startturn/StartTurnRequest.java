package com.dddimplement.exchange.application.startturn;

import com.buildingblocks.shared.application.Request;

public class StartTurnRequest extends Request {
    public StartTurnRequest(String aggregateId) {
        super(aggregateId);
    }
}