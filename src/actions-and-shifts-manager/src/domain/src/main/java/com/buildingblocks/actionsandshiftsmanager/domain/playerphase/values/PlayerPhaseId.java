package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class PlayerPhaseId extends Identity {
    public PlayerPhaseId(){
        super();
    }

    private PlayerPhaseId(String value){
        super(value);
    }

    public static PlayerPhaseId of(String value){
        return new PlayerPhaseId(value);
    }
}
