package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Percentage implements IValueObject {
    private final Integer value;

    private Percentage(final Integer value) {
        this.value = value;
        validate();
    }

    public static Percentage of(final Integer value) {
        return new Percentage(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Percentage");
        Validate.validatePositiveNumber(value, "Percentage");
    }

    public Integer getValue() {
        return value;
    }
}
