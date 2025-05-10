package com.buildingblocks.actionsandshiftsmanager.domain.movement.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ModifiedMethodPerformed extends DomainEvent {
    private String id;
    private String nameModifiedMethod;
    private String description;

    public ModifiedMethodPerformed(String id, String nameModifiedMethod, String description) {
        super(EventsEnum.MODIFIED_METHOD_PERFORMED.name());
        this.id = id;
        this.nameModifiedMethod = nameModifiedMethod;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getNameModifiedMethod() {
        return nameModifiedMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNameModifiedMethod(String nameModifiedMethod) {
        this.nameModifiedMethod = nameModifiedMethod;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
