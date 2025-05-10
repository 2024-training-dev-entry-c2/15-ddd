package com.buildingblocks.actionsandshiftsmanager.domain.movement.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

import static com.buildingblocks.shared.domain.utils.Validations.isNotEmptyString;

public class Description implements IValueObject {
    private final String description;

    private Description(String description) {
        this.description = description;
    }

    public static Description of(String description){
        return new Description(description);
    }

    @Override
    public void validate() {
        isNotEmptyString(description, "description cannot be null or empty");
    }

    public String getDescription() {
        return description;
    }
}
