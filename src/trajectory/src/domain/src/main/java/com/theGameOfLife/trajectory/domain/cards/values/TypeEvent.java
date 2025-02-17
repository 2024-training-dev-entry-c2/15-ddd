package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class TypeEvent implements IValueObject{
    private final TypeEventEnum typeEvent;

    private TypeEvent(TypeEventEnum typeEvent){
        this.typeEvent = typeEvent;
        validate();
    }

    public static TypeEvent of(TypeEventEnum typeEvent){
        return new TypeEvent(typeEvent);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotNull(typeEvent, "el tipo de evento no puede ser null");
        ValidateUtils.validateIsNotBlank(typeEvent, "el tipo de evento no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(typeEvent, "el tipo de evento es requerido");
        ValidateUtils.validateIsString(typeEvent, "el tipo de evento no puede ser un numero");
    }

    public TypeEventEnum getTypeEvent() {
        return typeEvent;
    }

}