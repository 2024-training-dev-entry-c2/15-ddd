package com.buildingblocks.actionsandshiftsmanager.domain.movement.entities;

import com.buildingblocks.actionsandshiftsmanager.domain.movement.values.Description;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.values.MethodPerformedId;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.values.Name;
import com.buildingblocks.shared.domain.generic.Entity;

public class MethodPerformed extends Entity<MethodPerformedId> {
    private Name name;
    private Description description;

    public MethodPerformed( Name name, Description description) {
        super(new MethodPerformedId());
        this.name = name;
        this.description = description;
    }

    public MethodPerformed(MethodPerformedId identity, Name name, Description description) {
        super(identity);
        this.name = name;
        this.description = description;
    }

    public boolean MethodActivated(){
        if (description.getDescription().contains("activated")){
            return true;
        }
        return false;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }
}
