package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.Identity;

public class GovernmentId extends Identity {

    public GovernmentId() {
        super();
    }

    private GovernmentId(String value) {
        super(value);
    }

    public static GovernmentId of(String value) {
        return new GovernmentId(value);
    }

}