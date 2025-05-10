package com.buildingblocks.actionsandshiftsmanager.domain.playerphase.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

import static com.buildingblocks.shared.domain.utils.Validations.isNotEmptyString;
import static com.buildingblocks.shared.domain.utils.Validations.validateRange;

public class AmountPoints implements IValueObject {
    private final int amountPoints;

    private AmountPoints(int amountPoints) {
        this.amountPoints = amountPoints;
    }

    public static AmountPoints of(int amountPoints){
        return new AmountPoints(amountPoints);
    }

    @Override
    public void validate() {
        validateRange(amountPoints,0,4, "Amount points out of range");
    }

    public int getAmountPoints() {
        return amountPoints;
    }
}
