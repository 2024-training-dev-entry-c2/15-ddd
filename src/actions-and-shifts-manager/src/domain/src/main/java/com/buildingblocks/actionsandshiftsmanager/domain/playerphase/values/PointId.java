package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class PointId extends Identity {
    public PointId(){
        super();
    }

    private PointId(String value){
        super(value);
    }

    public static PointId of(String value){
        return new PointId(value);
    }
}
