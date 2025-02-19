package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class DescriptionDecision implements IValueObject{
    private final String description;

    private DescriptionDecision(String description){
        this.description = description;
        validate();
    }

    public static DescriptionDecision of(String description){
        return new DescriptionDecision(description);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(description, "la descripcion de la decisión no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(description, "la descripcion de la decisión es requerida");
        ValidateUtils.validateIsString(description, "la descripcion de la decisión no puede ser un numero");
        ValidateUtils.validateIsNotNull(description, "la descripcion de la decisión no puede ser null");
    }

    public String getDescription() {
        return description;
    }
}