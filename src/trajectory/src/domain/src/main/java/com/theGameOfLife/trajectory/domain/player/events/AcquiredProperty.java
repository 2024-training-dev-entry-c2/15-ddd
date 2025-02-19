package com.theGameOfLife.trajectory.domain.player.events;
package com.theGameOfLife.shared.domain.generic;
import com.theGameOfLife.trajectory.domain.player.values.Address;
import com.theGameOfLife.trajectory.domain.player.values.TypeProperty;
import com.theGameOfLife.trajectory.domain.player.values.ValueProperty;

public class AcquiredProperty extends DomainEvent{

        private final Adress address;
        private final ValueProperty value;
        private final TypeProperty type;

    AcquiredProperty(Address address, ValueProperty value, TypeProperty type){
        super(this.EventsNameEnum.Acquired_Property.name());
        this.address = address;
        this.value = value;
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public ValueProperty getValue() {
        return value;
    }

    public TypeProperty getType() {
        return type;
    }


}