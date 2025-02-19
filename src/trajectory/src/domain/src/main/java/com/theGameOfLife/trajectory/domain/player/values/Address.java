package com.theGameOfLife.trajectory.domain.player.values;
package com.theGameOfLife.shared.domain.generic;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;
import com.theGameOfLife.shared.domain.generic.IvalueObject;

public class Address implements IvalueObject {

    private final String street;
    private final String number;
    private final String zone;

    private Address(String street, String number, String zone){
        this.street = street;
        this.number = number;
        this.zone = zone;
        validate();
    }

    public static Address of(String street, String number, String zone){
        return new Address(street, number, zone)
    }

   @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(street, "street no puede estar vacio");
        ValidateUtils.validateIsPositive(number, "el numero debe ser valido");
        ValidateUtils.validateIsNotBlank(zone, "la zona es requerida");
        ValidateUtils.validateIsNotEmpty(street, "street no puede ser null");
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getZone() {
        return zone;
    }
}