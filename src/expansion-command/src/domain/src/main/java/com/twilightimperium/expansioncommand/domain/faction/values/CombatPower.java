package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class CombatPower implements IValueObject {
    private final Integer value;

    private CombatPower(final Integer value) {
        this.value = value;
        validate();
    }

    public static CombatPower of(final Integer value) {
        return new CombatPower(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Combat Power");
        Validate.validatePositiveNumber(value, "Combat Power");
    }

    public Integer getValue() {
        return value;
    }
}
