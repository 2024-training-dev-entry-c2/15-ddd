package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class DescriptionReward implements IValueObject{
    private final String description;

    private DescriptionReward(String description){
        this.description = description;
        validate();
    }

    public static DescriptionReward of(String description){
        return new DescriptionReward(description);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(description, "la descripcion de la recompensa no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(description, "la descripcion de la recompensa es requerida");
        ValidateUtils.validateIsString(description, "la descripcion de la recompensa no puede ser un numero");
        ValidateUtils.validateIsNotNull(description, "la descripcion de la recompensa no puede ser null");
    }

    public String getDescription() {
        return description;
    }
}