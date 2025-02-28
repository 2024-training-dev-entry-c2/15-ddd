package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Name implements IValueObject {
    private final String value;

    private Name(final String value) {
        this.value = value;
        validate();
    }

    public static Name of(final String value) {
        return new Name(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Name");
        Validate.validateNotEmpty(value, "Name");
        Validate.validateAlphanumeric(value, "Name");
    }

    public String getValue() {
        return value;
    }
}
