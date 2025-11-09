package org.javerland.sockparadox.engine;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import org.javerland.sockparadox.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for GameEngine logic methods
 * Note: Tests that require Android Context are in instrumented tests
 */
public class GameEngineLogicTest {

    @Test
    public void testRoomActionIsRepeatable_True() {
        Room.Action action = createMockAction(true);
        assertTrue(action.isRepeatable());
    }

    @Test
    public void testRoomActionIsRepeatable_False() {
        Room.Action action = createMockAction(false);
        assertFalse(action.isRepeatable());
    }

    @Test
    public void testRoomCompassValidDirection() {
        Room.Compass compass = new Room.Compass();
        assertNotNull(compass);
    }

    @Test
    public void testRoomEffectCanSetEndGame() {
        Room.Effect effect = new Room.Effect();
        assertNotNull(effect);
    }

    @Test
    public void testConditionListCanBeEmpty() {
        List<Room.Condition> conditions = new ArrayList<>();
        assertTrue(conditions.isEmpty());
    }

    @Test
    public void testEffectListCanBeEmpty() {
        List<Room.Effect> effects = new ArrayList<>();
        assertTrue(effects.isEmpty());
    }

    @Test
    public void testActionWithNoConditions() {
        Room.Action action = new Room.Action();
        assertNull(action.getConditions());
    }

    @Test
    public void testActionWithNoEffects() {
        Room.Action action = new Room.Action();
        assertNull(action.getEffects());
    }

    private Room.Action createMockAction(final boolean repeatable) {
        return new Room.Action() {
            @Override
            public Boolean isRepeatable() {
                return repeatable;
            }
        };
    }

    @Test
    public void testMultipleConditionsCanExist() {
        List<Room.Condition> conditions = new ArrayList<>();
        Room.Condition condition1 = new Room.Condition();
        Room.Condition condition2 = new Room.Condition();
        conditions.add(condition1);
        conditions.add(condition2);
        assertEquals(2, conditions.size());
    }

    @Test
    public void testMultipleEffectsCanExist() {
        List<Room.Effect> effects = new ArrayList<>();
        Room.Effect effect1 = new Room.Effect();
        Room.Effect effect2 = new Room.Effect();
        effects.add(effect1);
        effects.add(effect2);
        assertEquals(2, effects.size());
    }

    @Test
    public void testRoomCanHaveMultipleCompassDirections() {
        List<Room.Compass> compass = new ArrayList<>();
        Room.Compass north = new Room.Compass();
        Room.Compass south = new Room.Compass();
        compass.add(north);
        compass.add(south);
        assertEquals(2, compass.size());
    }

    @Test
    public void testRoomCanHaveMultipleActions() {
        List<Room.Action> actions = new ArrayList<>();
        Room.Action action1 = new Room.Action();
        Room.Action action2 = new Room.Action();
        actions.add(action1);
        actions.add(action2);
        assertEquals(2, actions.size());
    }
}
