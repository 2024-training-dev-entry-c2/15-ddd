package com.buildingblocks.actionsandshiftsmanager.domain.movement;

import com.buildingblocks.actionsandshiftsmanager.domain.movement.events.ChosenPlace;
import com.buildingblocks.actionsandshiftsmanager.domain.movement.events.ModifiedMethodPerformed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {
    private Movement movement;

    @BeforeEach
    void setUp() {
        movement = new Movement();
    }

    @Test
    void addPlaceSuccess() {
        movement.choosePlace("city");
        assertEquals("city", movement.getPlace().getType().getType());
        assertInstanceOf(ChosenPlace.class, movement.getUncommittedEvents().get(0));
    }

    @Test
    void ModifiedMethodPerformedSuccess() {
        String id = "1";
        String name = "dicard card";
        String description = "card in this turn are discarted";

        movement.modifiedMethodPerformed(id, name, description);
        assertEquals(name, movement.getMethodPerformed().getName().getName());
        assertEquals(description, movement.getMethodPerformed().getDescription().getDescription());
        assertInstanceOf(ModifiedMethodPerformed.class, movement.getUncommittedEvents().get(0));
    }

    @Test
    void MovementRebuildAggregateSuccess() {
        movement = Movement.from("1", List.of(new ChosenPlace("city")));
        assertEquals("1", movement.getIdentity().getValue());
    }

}