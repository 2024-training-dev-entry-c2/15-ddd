package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class PlayerChanged extends DomainEvent {
    private String id;
    private String name;

    public PlayerChanged(String id, String name) {
        super(EventsEnum.PLAYER_CHANGED.name());
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
