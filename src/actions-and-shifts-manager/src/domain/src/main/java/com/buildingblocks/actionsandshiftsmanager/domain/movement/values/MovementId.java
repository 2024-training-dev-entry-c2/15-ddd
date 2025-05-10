package com.buildingblocks.actionsandshiftsmanager.domain.movement.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class MovementId extends Identity {
    public MovementId(){
        super();
    }

    private MovementId(String value){
        super(value);
    }

    public static MovementId of(String value){
        return new MovementId(value);
    }
}
