package org.javerland.sockparadox.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit tests for Room model class
 */
public class RoomTest {

    private Room room;

    @Before
    public void setUp() {
        room = new Room();
    }

    @Test
    public void testRoomCode() {
        assertNull(room.getRoomCode());
    }

    @Test
    public void testRoomNameKey() {
        assertNull(room.getNameKey());
    }

    @Test
    public void testRoomDescriptionKey() {
        assertNull(room.getDescriptionKey());
    }

    @Test
    public void testRoomImageResource() {
        assertNull(room.getImageResource());
    }

    @Test
    public void testRoomObjects() {
        assertNull(room.getObjects());
    }

    @Test
    public void testRoomNpcs() {
        assertNull(room.getNpcs());
    }

    @Test
    public void testRoomCompass() {
        assertNull(room.getCompass());
    }

    @Test
    public void testRoomActions() {
        assertNull(room.getActions());
    }

    @Test
    public void testCompassDirection() {
        Room.Compass compass = new Room.Compass();
        assertNull(compass.getDirection());
    }

    @Test
    public void testCompassWayTo() {
        Room.Compass compass = new Room.Compass();
        assertNull(compass.getWayTo());
    }

    @Test
    public void testActionCommandKey() {
        Room.Action action = new Room.Action();
        assertNull(action.getCommandKey());
    }

    @Test
    public void testActionConditions() {
        Room.Action action = new Room.Action();
        assertNull(action.getConditions());
    }

    @Test
    public void testActionResultKey() {
        Room.Action action = new Room.Action();
        assertNull(action.getResultKey());
    }

    @Test
    public void testActionEffects() {
        Room.Action action = new Room.Action();
        assertNull(action.getEffects());
    }

    @Test
    public void testActionIsRepeatable_Null() {
        Room.Action action = new Room.Action();
        assertFalse(action.isRepeatable());
    }

    @Test
    public void testConditionHasObject() {
        Room.Condition condition = new Room.Condition();
        assertNull(condition.getHasObject());
    }

    @Test
    public void testEffectUnlockRoom() {
        Room.Effect effect = new Room.Effect();
        assertNull(effect.getUnlockRoom());
    }

    @Test
    public void testEffectAddToInventory() {
        Room.Effect effect = new Room.Effect();
        assertNull(effect.getAddToInventory());
    }

    @Test
    public void testEffectEndGame() {
        Room.Effect effect = new Room.Effect();
        assertNull(effect.getEndGame());
    }
}
