package com.theGameOfLife.trajectory.domain.player.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class Name implements IValueObject {

    private final String name;

    private Name(String name){
        this.name = name;
        validate();
    }

    public static Name of(String name){
        return new Name(name);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(name, "el nombre no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(name, "el nombre no puede ser null");
        ValidateUtils.validateIsString(name, "el nombre no puede ser un numero");
    }

    public String getName() {
        return name;
    }

}