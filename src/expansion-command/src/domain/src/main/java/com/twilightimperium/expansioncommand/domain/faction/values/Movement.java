package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Movement implements IValueObject {
    private final Integer value;

    private Movement(final Integer value) {
        this.value = value;
        validate();
    }

    public static Movement of(final Integer value) {
        return new Movement(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Movement Power");
        Validate.validatePositiveNumber(value, "Movement Power");
    }

    public Integer getValue() {
        return value;
    }
}
