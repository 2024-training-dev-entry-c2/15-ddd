package com.dddimplement.exchange.application.buildphase;

import com.buildingblocks.shared.application.Request;

public class BuildPhaseRequest extends Request {
    private String playerId;
    private boolean buildCity;
    private boolean buildPath;
    private boolean buildSettlement;
    private String territoryType;

    public BuildPhaseRequest(String aggregateId ,String playerId, boolean buildCity, boolean buildPath, boolean buildSettlement, String territoryType) {
        super(aggregateId);
        this.playerId = playerId;
        this.buildCity = buildCity;
        this.buildPath = buildPath;
        this.buildSettlement = buildSettlement;
        this.territoryType = territoryType;
    }

    public String getPlayerId() {
        return playerId;
    }

    public boolean isBuildCity() {
        return buildCity;
    }

    public boolean isBuildPath() {
        return buildPath;
    }

    public boolean isBuildSettlement() {
        return buildSettlement;
    }

    public String getTerritoryType() {
        return territoryType;
    }

}