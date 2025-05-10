package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

import static com.buildingblocks.shared.domain.utils.Validations.isNotEmptyString;

public class Action implements IValueObject {
    private final String action;

    private Action(String action) {
        this.action = action;
    }

    public static Action of(String action){
        return new Action(action);
    }

    @Override
    public void validate() {
        isNotEmptyString(action, "action cannot be null or empty");
    }

    public String getAction() {
        return action;
    }
}
