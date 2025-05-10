package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.entities;

import com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values.PointId;
import com.buildingblocks.shared.domain.generic.Entity;

public class Points extends Entity<PointId> {
    private int amountPoints;

    public Points(int amountPoints) {
        super(new PointId());
        this.amountPoints = amountPoints;
    }

    public Points(PointId identity, int amountPoints) {
        super(identity);
        this.amountPoints = amountPoints;
    }

    public void PointDecresed() {
        this.amountPoints--;
    }

    public int getAmountPoints() {
        return amountPoints;
    }

    public void setAmountPoints(int amountPoints) {
        this.amountPoints = amountPoints;
    }
}
