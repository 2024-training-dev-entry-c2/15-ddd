package com.twilightimperium.expansioncommand.domain.system.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class InvadingUnitCount implements IValueObject {
    private final Integer value;

    private InvadingUnitCount(final Integer value) {
        this.value = value;
        validate();
    }

    public static InvadingUnitCount of(final Integer value) {
        return new InvadingUnitCount(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Invading Units");
        Validate.validatePositiveNumber(value, "Invading Units");
    }

    public Integer getValue() {
        return value;
    }
}