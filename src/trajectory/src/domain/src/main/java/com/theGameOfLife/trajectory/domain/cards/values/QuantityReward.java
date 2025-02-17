package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class QuantityReward implements IValueObject{
    private final int quantity;

    private QuantityReward(int quantity){
        this.quantity = quantity;
        validate();
    }

    public static QuantityReward of(int quantity){
        return new QuantityReward(quantity);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsPositive(quantity, "la cantidad de recompensa debe ser valida");
        ValidateUtils.validateIsNotNull(quantity, "la cantidad de recompensa no puede ser null");
        ValidateUtils.validateIsNotBlank(quantity, "la cantidad de recompensa no puede estar vacio");
        ValidateUtils.validateMaxLength(quantity, 10000, "la cantidad de recompensa no puede ser mayor a 10000");
    }

    public int getQuantity() {
        return quantity;
    }
}