package com.theGameOfLife.shared.domain.generic;

import java.util.UUID;

public abstract class Identity {

    private final String value;

    Identity(){
        this.value = UUID.randomUUID().toString();
    }

    Identity(final String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
