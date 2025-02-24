package com.dddimplement.exchange.domain.player.values;

import com.dddimplement.shared.domain.generic.IValueObject;

public class ResourceType implements IValueObject {
    private final  ResourceTypeEnum type;
    private final Amount amount;

    public ResourceType(ResourceTypeEnum type, Amount amount) {
        this.type = type;
        this.amount = amount;
        validate();
    }

    public static ResourceType of(ResourceTypeEnum type, Amount amount)
    {
        return new ResourceType(type, amount);
    }

    public Amount getAmount() {
        return amount;
    }

    public ResourceTypeEnum getType() {
        return type;
    }

    @Override
    public void validate() {
        if (this.type == null) {
            throw new IllegalArgumentException("Resource type cannot be null");
        }
    }
}
