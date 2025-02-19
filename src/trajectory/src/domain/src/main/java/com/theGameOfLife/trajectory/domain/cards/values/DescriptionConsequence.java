package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class DescriptionConsequence implements IValueObject{
    private final String description;

    private DescriptionConsequence(String description){
        this.description = description;
        validate();
    }

    public static DescriptionConsequence of(String description){
        return new DescriptionConsequence(description);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(description, "la descripcion de la consecuencia no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(description, "la descripcion de la consecuencia es requerida");
        ValidateUtils.validateIsString(description, "la descripcion de la consecuencia no puede ser un numero");
        ValidateUtils.validateIsNotNull(description, "la descripcion de la consecuencia no puede ser null");
    }

    public String getDescription() {
        return description;
    }
}