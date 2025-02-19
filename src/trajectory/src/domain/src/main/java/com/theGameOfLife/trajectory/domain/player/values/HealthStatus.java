package com.theGameOfLife.trajectory.domain.player.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;
import com.theGameOfLife.trajectory.domain.player.values.StatusSaludEnum;

public class HealthStatus implements IValueObject {
    private final StatusSaludEnum health;

    private HealthStatus(StausSaludEnum health){
        this.health = health;
        validate();
    }

    public static HealthStatus of(StatusSaludEnum health){
        return new HealthStatus(health);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotNull(health, "el valor de la salud no puede ser null");
        ValidateUtils.validateIsNotBlank(health, "el valor de la salud no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(health, "el valor de la salud es requerido");
    }

    public StatusSaludEnum getHealth() {
        return health;
    }


}