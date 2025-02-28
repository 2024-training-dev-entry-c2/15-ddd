package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.Identity;

public class UnitId extends Identity {

    public UnitId() {
        super();
    }

    private UnitId(String value) {
        super(value);
    }

    public static UnitId of(String value) {
        return new UnitId(value);
    }
}
