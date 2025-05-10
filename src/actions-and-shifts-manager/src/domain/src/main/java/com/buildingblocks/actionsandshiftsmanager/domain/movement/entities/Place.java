package com.buildingblocks.actionsandshiftsmanager.domain.movement.entities;

import com.buildingblocks.actionsandshiftsmanager.domain.movement.values.PlaceId;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.values.Type;
import com.buildingblocks.shared.domain.generic.Entity;

public class Place extends Entity<PlaceId> {

    private Type type;
    private int diseaseLevel;

    public Place(Type type, int diseaseLevel) {
        super(new PlaceId());
        canGo(type,diseaseLevel);
    }

    public Place(PlaceId identity, Type type, int diseaseLevel) {
        super(identity);
        canGo(type,diseaseLevel);
    }

    public void canGo(Type type, int diseaseLevel) {
        if (diseaseLevel >= 3) {
            throw new Error("the disease level is very high, cant go");
        }else {
            this.type = type;
            this.diseaseLevel = diseaseLevel;
        }
    }

    public Type getType() {
        return type;
    }

    public int getDiseaseLevel() {
        return diseaseLevel;
    }
}
