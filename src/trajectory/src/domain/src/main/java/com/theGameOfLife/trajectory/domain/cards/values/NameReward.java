package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class NameReward implements IValueObject{
    private final String name;

    private NameReward(String name){
        this.name = name;
        validate();
    }

    public static NameReward of(String name){
        return new NameReward(name);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(name, "el nombre de la recompensa no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(name, "el nombre de la recompensa es requerida");
        ValidateUtils.validateIsString(name, "el nombre de la recompensa no puede ser un numero");
        ValidateUtils.validateIsNotNull(name, "el nombre de la recompensa no puede ser null");
    }

    public String getName() {
        return name;
    }
}