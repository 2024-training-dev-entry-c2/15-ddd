package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Description implements IValueObject {
    private final String value;

    private Description(final String value) {
        this.value = value;
        validate();
    }

    public static Description of(final String value) {
        return new Description(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Description");
        Validate.validateNotEmpty(value, "Description");
        Validate.validateAlphanumeric(value, "Description");
    }

    public String getValue() {
        return value;
    }
}
