package com.theGameOfLife.trajectory.domain.player.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class ValueProperty implements IValueObject{
    private final Long value;

    private ValueProperty(Long value){
        this.value = value;
        validate();
    }

    public static ValueProperty of(Long value){
        return new ValueProperty(value);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsPositive(value, "el valor de la propiedad debe ser valido");
        ValidateUtils.validateIsNotNull(value, "el valor de la propiedad no puede ser null");
        ValidateUtils.validateIsNotBlank(value, "el valor de la propiedad no puede estar vacio");
        ValidateUtils.validateMaxLength(value, 10000, "el valor de la propiedad no puede ser mayor a 10000");
    }

    public Long getValue() {
        return value;
    }
}