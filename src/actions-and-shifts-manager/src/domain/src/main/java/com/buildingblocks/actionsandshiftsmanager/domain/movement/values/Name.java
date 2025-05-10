package com.buildingblocks.actionsandshiftsmanager.domain.movement.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

import static com.buildingblocks.shared.domain.utils.Validations.isNotEmptyString;

public class Name implements IValueObject {
    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name){
        return new Name(name);
    }

    @Override
    public void validate() {
        isNotEmptyString(name, "name cannot be null or empty");
    }

    public String getName() {
        return name;
    }
}
