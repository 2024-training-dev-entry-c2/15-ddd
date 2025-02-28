package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.Identity;

public class ConqueredFactionId extends Identity {

    public ConqueredFactionId() {
        super();
    }

    private ConqueredFactionId(String value) {
        super(value);
    }

    public static ConqueredFactionId of(String value) {
        return new ConqueredFactionId(value);
    }

}