package org.javerland.sockparadox.engine;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.javerland.sockparadox.model.Room;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Instrumented tests for GameEngine that require Android Context
 * These tests will execute on an Android device or emulator
 */
@RunWith(AndroidJUnit4.class)
public class GameEngineInstrumentedTest {

    private GameEngine gameEngine;
    private Context context;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        gameEngine = new GameEngine(context);
    }

    @Test
    public void testGameEngineInitialization() {
        assertNotNull(gameEngine);
    }

    @Test
    public void testCurrentRoomIsNotNull() {
        Room currentRoom = gameEngine.getCurrentRoom();
        assertNotNull("Current room should not be null", currentRoom);
    }

    @Test
    public void testInitialRoomIsBedroom() {
        Room currentRoom = gameEngine.getCurrentRoom();
        assertEquals("Initial room should be BEDROOM", "BEDROOM", currentRoom.getRoomCode());
    }

    @Test
    public void testInventoryStartsWithSock() {
        Set<String> inventory = gameEngine.getInventory();
        assertTrue("Inventory should contain SOCK1", inventory.contains("SOCK1"));
    }

    @Test
    public void testCanMoveToUnlockedRoom() {
        assertTrue("Should be able to move to HALLWAY", gameEngine.canMoveToRoom("HALLWAY"));
    }

    @Test
    public void testCannotMoveToLockedRoom() {
        // Assuming WASHING_MACHINE_INSIDE is initially locked
        assertFalse("Should not be able to move to locked room initially", 
                   gameEngine.canMoveToRoom("WASHING_MACHINE_INSIDE"));
    }

    @Test
    public void testMoveToValidRoom() {
        gameEngine.moveToRoom("HALLWAY");
        Room currentRoom = gameEngine.getCurrentRoom();
        assertEquals("Current room should be HALLWAY", "HALLWAY", currentRoom.getRoomCode());
    }

    @Test
    public void testGetAvailableDirections() {
        List<Room.Compass> directions = gameEngine.getAvailableDirections();
        assertNotNull("Directions should not be null", directions);
    }

    @Test
    public void testGetAvailableActions() {
        List<Room.Action> actions = gameEngine.getAvailableActions();
        assertNotNull("Actions should not be null", actions);
    }

    @Test
    public void testGameNotEndedInitially() {
        assertFalse("Game should not be ended initially", gameEngine.isGameEnded());
    }

    @Test
    public void testGetStringReturnsNotNull() {
        String roomName = gameEngine.getString("room_bedroom_name");
        assertNotNull("String resource should not be null", roomName);
    }

    @Test
    public void testGetNpcExists() {
        // Assuming there's an NPC in the game data
        assertNotNull("Game should have NPCs loaded", gameEngine.getNpc("MOM"));
    }

    @Test
    public void testGetObjectExists() {
        // Assuming SOCK1 exists in game data
        assertNotNull("Game should have objects loaded", gameEngine.getObject("SOCK1"));
    }

    @Test
    public void testInventoryIsNotNull() {
        Set<String> inventory = gameEngine.getInventory();
        assertNotNull("Inventory should not be null", inventory);
    }

    @Test
    public void testInventoryIsMutable() {
        Set<String> inventory1 = gameEngine.getInventory();
        Set<String> inventory2 = gameEngine.getInventory();
        // Should return different instances (defensive copy)
        assertNotSame("Inventory should return defensive copies", inventory1, inventory2);
    }

    @Test
    public void testRoomHasNameKey() {
        Room currentRoom = gameEngine.getCurrentRoom();
        assertNotNull("Room should have name key", currentRoom.getNameKey());
    }

    @Test
    public void testRoomHasDescriptionKey() {
        Room currentRoom = gameEngine.getCurrentRoom();
        assertNotNull("Room should have description key", currentRoom.getDescriptionKey());
    }

    @Test
    public void testRoomHasImageResource() {
        Room currentRoom = gameEngine.getCurrentRoom();
        assertNotNull("Room should have image resource", currentRoom.getImageResource());
    }
}
