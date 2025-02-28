package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Type implements IValueObject {
    private final String value;

    private Type(final String value) {
        this.value = value;
        validate();
    }

    public static Type of(final String value) {
        return new Type(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Type");
        Validate.validateNotEmpty(value, "Type");
        Validate.validateAlphanumeric(value, "Type");
    }

    public String getValue() {
        return value;
    }
}
