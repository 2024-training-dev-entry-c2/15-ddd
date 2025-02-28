package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.Identity;

public class FactionId extends Identity {

    public FactionId() {
        super();
    }

    private FactionId(String value) {
        super(value);
    }

    public static FactionId of(String value) {
        return new FactionId(value);
    }

}
