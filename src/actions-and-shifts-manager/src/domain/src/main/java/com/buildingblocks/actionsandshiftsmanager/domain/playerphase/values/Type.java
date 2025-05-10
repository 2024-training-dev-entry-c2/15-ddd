package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

import static com.buildingblocks.shared.domain.utils.Validations.isNotEmptyString;

public class Type implements IValueObject {
    private final String type;

    private Type(String type) {
        this.type = type;
    }

    public static Type of(String type) {
        return new Type(type);
    }

    @Override
    public void validate() {
        isNotEmptyString(type, "type cannot be null or empty");
    }

    public String getType() {
        return type;
    }
}
