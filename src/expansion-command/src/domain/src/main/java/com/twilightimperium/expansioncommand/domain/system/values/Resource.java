package com.twilightimperium.expansioncommand.domain.system.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Resource implements IValueObject {
    private final Integer value;

    private Resource(final Integer value) {
        this.value = value;
        validate();
    }

    public static Resource of(final Integer value) {
        return new Resource(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Resources");
        Validate.validatePositiveNumber(value, "Resources");
    }

    public Integer getValue() {
        return value;
    }
}
