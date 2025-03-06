package com.monopoly.monopoly_managment.domain.bank_account.values;

import com.monopoly.shared.domain.generic.IValueObject;
import com.monopoly.shared.domain.utils.Validator;

public class Origin implements IValueObject {
    private final String value;

    private Origin(final String value) {
        this.value = value;
        validate();
    }

    public static Origin of(final String value) {
        return new Origin(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validateNull(this.value, "Origin value");
    }
}
