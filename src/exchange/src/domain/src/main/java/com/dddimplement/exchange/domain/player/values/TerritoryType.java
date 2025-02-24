package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class TerritoryType implements IValueObject  {
    private final  TerritoryTypeEnum type;

    private TerritoryType(TerritoryTypeEnum type) {
        this.type = type;
        validate();
    }

    public static TerritoryType of(TerritoryTypeEnum type) {
        return new TerritoryType(type);
    }

    @Override
    public void validate() {
        if(this.type == null) {
            throw new IllegalArgumentException("TerritoryType cannot be null");
        }
    }
}
