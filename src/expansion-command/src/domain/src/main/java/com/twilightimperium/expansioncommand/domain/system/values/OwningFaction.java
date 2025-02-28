package com.twilightimperium.expansioncommand.domain.system.values;

import com.twilightimperium.shared.domain.generic.IValueObject;
import com.twilightimperium.shared.domain.utils.Validate;

public class OwningFaction implements IValueObject {
    private final String value;

    private OwningFaction(final String value) {
        this.value = value;
        validate();
    }

    public static OwningFaction of(final String value) {
        return new OwningFaction(value);
    }

    @Override
    public void validate() {
        Validate.validateNotNull(value, "Owning Faction");
        Validate.validateNotEmpty(value, "Owning Faction");
        Validate.validateAlphanumeric(value, "Owning Faction");
    }

    public String getValue() {
        return value;
    }
}