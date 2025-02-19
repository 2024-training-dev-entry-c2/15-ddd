package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class NameConsequence implements IValueObject{
    private final String name;

    private NameConsequence(String name){
        this.name = name;
        validate();
    }

    public static NameConsequence of(String name){
        return new NameConsequence(name);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(name, "el nombre de la consecuencia no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(name, "el nombre de la consecuencia es requerida");
        ValidateUtils.validateIsString(name, "el nombre de la consecuencia no puede ser un numero");
        ValidateUtils.validateIsNotNull(name, "el nombre de la consecuencia no puede ser null");
    }

    public String getName() {
        return name;
    }
}