package com.theGameOfLife.trajectory.domain.player.values;

public class NameProfession implements IValueObject{
    private final String nameValue;

    private NameProfession(String nameValue){
        this.nameValue = nameValue;
        validate();
    }

    public static NameProfession of(String nameValue){
        return new NameProfession(nameValue);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(nameValue, "el nombre de la profesion no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(nameValue, "el nombre de la profesion no puede ser null");
        ValidateUtils.validateIsString(nameValue, "el nombre de la profesion no puede ser un numero");
    }

    public String getNameValue() {
        return nameValue;
    }
}