package com.theGameOfLife.trajectory.domain.player.values;

import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class Diner implements IValueObject {
    private final long amount;

    private Diner(long amount){
        this.amount = amount;
        validate();
    }

    public static Diner of(long amount){
        return new Diner(amount);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsPositive(amount, "La cantidad de dinero debe ser valida");
        ValidateUtils.validateIsNotNull(amount, "el dinero no puede ser null");
        ValidateUtils.validateIsNotBlank(amount, "el dinero no puede estar vacio");
        ValidateUtils.validateMaxLength(amount, 1000, "el dinero no puede ser mayor a 1000");
    }

    public long getAmount() {
        return amount;
    }
}