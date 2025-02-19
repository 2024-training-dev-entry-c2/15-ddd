package com.theGameOfLife.trajectory.domain.player.entities;
import com.theGameOfLife.trajectory.domain.player.values.Address;
import com.theGameOfLife.trajectory.domain.player.values.PlayerId;
import com.theGameOfLife.trajectory.domain.player.values.TypePropertyEnum;
import com.theGameOfLife.trajectory.domain.player.values.ValueProperty;
import com.theGameOfLife.shared.domain.generic.Identity;
public class Properties extends Entity<PlayerId>{
    private typeProperty type;
    private valueProperty value;
    private Address address;

    Properties(typeProperty type, valueProperty value, Address address){
        super(new PlayerId());
        this.type = type;
        this.value = value;
        this.address = address;
    }

    public Properties(Identity id, typeProperty type, valueProperty value, Address address){
        super(id);
        this.type = type;
        this.value = value;
        this.address = address;
    }

    public typeProperty getType() {
        return type;
    }

    public valueProperty getValue() {
        return value;
    }

    public Address getAddress() {
        return address;
    }

    public changeTipo(typeProperty type){
        typeProperty.getTypeProperty() == TypePropertyEnum.MANSION ?
                typeProperty.setTypeProperty(TypePropertyEnum.HOUSE) :
                typeProperty.setTypeProperty(TypePropertyEnum.MANSION);
    }

    public buyProperty(valueProperty value,){
        const value = Properties.getValue().getValue();
        const salaryActual = Salary.getSalary().getAmount();
        if(value > salaryActual){
            return system.out.println("No tienes fondos suficientes");
        }else{
            system.out.println("Neto:" + value);
        }
    }

    public sellProperty(valueProperty value){
        const amount = 1500;
        const salaryActual = Salary.getSalary().getAmount();
        const porcentaje = 16;
        const total = salaryActual + (amount * porcentaje) / 100;
        Salary.setSalary(total);
    }

    public setAddress(Address address){
        this.address = address;
    }
}