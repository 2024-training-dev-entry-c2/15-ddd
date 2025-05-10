package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CardDeleted extends DomainEvent {
    private String id;
    private String name;
    private String type;
    private String effect;

    public CardDeleted(String id, String name, String type, String effect) {
        super(EventsEnum.CARD_DELETED.name());
        this.id = id;
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

    public void setId(String id) {
        this.id = id;
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
