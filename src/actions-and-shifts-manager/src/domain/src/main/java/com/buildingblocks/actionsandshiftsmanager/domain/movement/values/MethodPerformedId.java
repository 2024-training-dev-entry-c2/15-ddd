package com.buildingblocks.actionsandshiftsmanager.domain.movement.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class MethodPerformedId extends Identity {
    public MethodPerformedId(){
        super();
    }

    private MethodPerformedId(String value){
        super(value);
    }

    public static MethodPerformedId of(String value){
        return new MethodPerformedId(value);
    }
}
