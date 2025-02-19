package com.theGameOfLife.trajectory.domain.player.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class State implements IValueObject{
    private final StatePlayerEnum statePlayer;

    private State(StatePlayerEnum statePlayer){
        this.statePlayer = statePlayer;
        validate();
    }

    public static State of(StatePlayerEnum statePlayer){
        return new State(statePlayer);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotNull(statePlayer, "el estado no puede ser null");
        ValidateUtils.validateIsNotBlank(statePlayer, "el estado no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(statePlayer, "el estado es requerido");
    }

    public getStatePlayer() {
        return statePlayer;
    }

    public StatePlayerEnum getStatePlayer() {
        return statePlayer;
    }
}