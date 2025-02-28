package com.twilightimperium.expansioncommand.domain.system.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Influence implements IValueObject {
    private final Integer value;

    private Influence(final Integer value) {
        this.value = value;
        validate();
    }

    public static Influence of(final Integer value) {
        return new Influence(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Influence");
        Validate.validatePositiveNumber(value, "Influence");
    }

    public Integer getValue() {
        return value;
    }
}