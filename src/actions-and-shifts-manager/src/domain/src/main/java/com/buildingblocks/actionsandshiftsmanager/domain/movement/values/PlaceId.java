package com.buildingblocks.actionsandshiftsmanager.domain.movement.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class PlaceId extends Identity {
    public PlaceId(){
        super();
    }

    private PlaceId(String value){
        super(value);
    }

    public static PlaceId of(String value){
        return new PlaceId(value);
    }
}
