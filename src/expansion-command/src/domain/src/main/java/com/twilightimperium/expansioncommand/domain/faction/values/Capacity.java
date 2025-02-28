package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Capacity implements IValueObject {
    private final Integer value;

    private Capacity(final Integer value) {
        this.value = value;
        validate();
    }

    public static Capacity of(final Integer value) {
        return new Capacity(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Capacity");
        Validate.validatePositiveNumber(value, "Capacity");
    }

    public Integer getValue() {
        return value;
    }
}
