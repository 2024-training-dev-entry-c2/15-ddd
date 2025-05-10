package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ModifiedPoints extends DomainEvent {
    private String id;
    private int amountPoints;

    public ModifiedPoints(String id, int amountPoints) {
        super(EventsEnum.MODIFIED_POINTS.name());
        this.id = id;
        this.amountPoints = amountPoints;
    }

    public int getAmountPoints() {
        return amountPoints;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmountPoints(int amountPoints) {
        this.amountPoints = amountPoints;
    }
}
