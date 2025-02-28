package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class Level implements IValueObject {
    private final Integer value;

    private Level(final Integer value) {
        this.value = value;
        validate();
    }

    public static Level of(final Integer value) {
        return new Level(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Level");
        Validate.validateMoreThanOne(value, "Level");
    }

    public Integer getValue() {
        return value;
    }
}
