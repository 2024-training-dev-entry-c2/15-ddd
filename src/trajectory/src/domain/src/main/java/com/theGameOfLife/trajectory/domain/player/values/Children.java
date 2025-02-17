package com.theGameOfLife.trajectory.domain.player.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class Children implements IValueObject {
    private final int numberOfChildren;

    private Children(int numberOfChildren){
        this.numberOfChildren = numberOfChildren;
        validate();
    }

    public static Children of(int numberOfChildren){
        return new Children(numberOfChildren);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsPositive(numberOfChildren, "el numero de hijos debe ser valido");
        ValidateUtils.validateMaxLength(numberOfChildren, 4, "el numero de hijos no debe ser mayor a 4");
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }
}