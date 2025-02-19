package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;


public class NameDecision implements IValueObject{
    private final String name;

    private NameDecision(String name){
        this.name = name;
        validate();
    }

    public static NameDecision of(String name){
        return new NameDecision(name);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(name, "el nombre de la decisión no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(name, "el nombre de la decisión es requerida");
        ValidateUtils.validateIsString(name, "el nombre de la decisión no puede ser un numero");
        ValidateUtils.validateIsNotNull(name, "el nombre de la decisión no puede ser null");
    }

    public String getName() {
        return name;
    }
}