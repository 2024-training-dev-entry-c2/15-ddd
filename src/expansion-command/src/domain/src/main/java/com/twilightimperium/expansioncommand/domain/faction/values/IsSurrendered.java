package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class IsSurrendered implements IValueObject {
    private final Boolean value;

    private IsSurrendered(final Boolean value) {
        this.value = value;
        validate();
    }

    public static IsSurrendered of(final Boolean value) {
        return new IsSurrendered(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "IsSurrendered");
    }

    public Boolean getValue() {
        return value;
    }
}
