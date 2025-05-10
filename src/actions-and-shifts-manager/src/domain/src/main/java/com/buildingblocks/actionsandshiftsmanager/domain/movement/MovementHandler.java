package com.buildingblocks.actionsandshiftsmanager.domain.movement;

import com.buildingblocks.actionsandshiftsmanager.domain.movement.entities.MethodPerformed;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.entities.Place;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.events.ChosenPlace;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.events.ModifiedMethodPerformed;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.values.Description;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.values.Name;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.values.Type;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;

public class MovementHandler extends DomainActionsContainer {
    public MovementHandler(Movement movement) {
        add((ChosenPlace event) -> {
            Place place = new Place( Type.of(event.getType()), 1);
            movement.setPlace(place);
        });
        add((ModifiedMethodPerformed event) -> {
            MethodPerformed methodPerformed = new MethodPerformed(Name.of(event.getNameModifiedMethod()), Description.of(event.getDescription()));
            movement.setMethodPerformed(methodPerformed);
        });
    }
}
