package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class AssignedCard extends DomainEvent {
    private String name;
    private String type;
    private String effect;

    public AssignedCard(String name, String type, String effect) {
        super(EventsEnum.ASSIGNED_CARD.name());
        this.name = name;
        this.type = type;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getEffect() {
        return effect;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

}
