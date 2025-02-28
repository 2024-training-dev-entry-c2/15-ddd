package com.twilightimperium.expansioncommand.domain.system.values;

import com.twilightimperium.shared.domain.generic.Identity;

public class PlanetId extends Identity {

    public PlanetId() {
        super();
    }

    private PlanetId(String value) {
        super(value);
    }

    public static PlanetId of(String value) {
        return new PlanetId(value);
    }

}