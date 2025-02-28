package com.twilightimperium.expansioncommand.domain.system.values;

import com.twilightimperium.shared.domain.generic.Identity;

public class SystemId extends Identity {

    public SystemId() {
        super();
    }

    private SystemId(String value) {
        super(value);
    }

    public static SystemId of(String value) {
        return new SystemId(value);
    }

}