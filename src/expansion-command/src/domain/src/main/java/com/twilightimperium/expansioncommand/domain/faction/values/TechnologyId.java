package com.twilightimperium.expansioncommand.domain.faction.values;

import com.twilightimperium.shared.domain.generic.Identity;

public class TechnologyId extends Identity {

    public TechnologyId() {
        super();
    }

    private TechnologyId(String value) {
        super(value);
    }

    public static TechnologyId of(String value) {
        return new TechnologyId(value);
    }

}