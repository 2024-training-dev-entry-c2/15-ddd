package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Cost implements IValueObject {
    private final Integer value;

    private Cost(final Integer value) {
        this.value = value;
        validate();
    }

    public static Cost of(final Integer value) {
        return new Cost(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Cost");
        Validate.validateMoreThanOne(value, "Cost");
    }

    public Integer getValue() {
        return value;
    }
}
