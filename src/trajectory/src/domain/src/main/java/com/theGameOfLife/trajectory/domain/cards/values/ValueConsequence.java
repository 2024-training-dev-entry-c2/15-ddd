package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class ValueConsequence implements IValueObject{
    private final Long value;

    private ValueConsequence(Long value){
        this.value = value;
        validate();
    }

    public static ValueConsequence of(Long value){
        return new ValueConsequence(value);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsPositive(value, "el valor de la consecuencia debe ser valida");
        ValidateUtils.validateIsNotNull(value, "el valor de la consecuencia no puede ser null");
        ValidateUtils.validateIsNotBlank(value, "el valor de la consecuencia no puede estar vacio");
        ValidateUtils.validateMaxLength(value, 10000, "el valor de la consecuencia no puede ser mayor a 10000");
    }

    public Long getValue() {
        return value;
    }

}