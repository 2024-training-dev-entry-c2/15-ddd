package com.theGameOfLife.trajectory.domain.player.events;
package com.theGameOfLife.shared.domain.generic;
public class AcquiredProperty extends DomainEvent{

        private final String street;
        private final String number;
        private final String zone;
        private final number value;
        private final TypeProperty type;

    AcquiredProperty(){
        super(this.EventsNameEnum.Acquired_Property.name());
    }

}