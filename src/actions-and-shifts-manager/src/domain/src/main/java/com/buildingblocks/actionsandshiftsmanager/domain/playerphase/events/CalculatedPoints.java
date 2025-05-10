package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CalculatedPoints extends DomainEvent {
    private int amountPoints;

    public CalculatedPoints(int amountPoints) {
        super(EventsEnum.CALCULATED_POINTS.name());
        this.amountPoints = amountPoints;
    }

    public int getAmountPoints() {
        return amountPoints;
    }

    public void setAmountPoints(int amountPoints) {
        this.amountPoints = amountPoints;
    }
}
