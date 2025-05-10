package com.buildingblocks.actionsandshiftsmanager.domain.movement.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ChosenPlace extends DomainEvent {
    private String type;

    public ChosenPlace(String type) {
        super(EventsEnum.CHOSEN_PLACE.name());
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
