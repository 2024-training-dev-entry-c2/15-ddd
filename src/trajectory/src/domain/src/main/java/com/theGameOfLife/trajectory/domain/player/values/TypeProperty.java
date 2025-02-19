package com.theGameOfLife.trajectory.domain.player.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class TypeProperty implements IValueObject{
    private final TypePropertyEnum typeProperty;

    private TypeProperty(TypePropertyEnum typeProperty){
        this.typeProperty = typeProperty;
        validate();
    }

    public static TypeProperty of(TypePropertyEnum typeProperty){
        return new TypeProperty(typeProperty);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(typeProperty, "el tipo de propiedad no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(typeProperty, "el tipo de propiedad no puede ser null");
        ValidateUtils.validateIsString(typeProperty, "el tipo de propiedad no puede ser un numero");
    }

    public TypePropertyEnum getTypeProperty() {
        return typeProperty;
    }
}