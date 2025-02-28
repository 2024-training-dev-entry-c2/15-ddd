package com.twilightimperium.expansioncommand.domain.system.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class SystemNumber implements IValueObject {
    private final Integer value;

    private SystemNumber(final Integer value) {
        this.value = value;
        validate();
    }

    public static SystemNumber of(final Integer value) {
        return new SystemNumber(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "System Number");
        Validate.validatePositiveNumber(value, "System Number");
    }

    public Integer getValue() {
        return value;
    }
}
