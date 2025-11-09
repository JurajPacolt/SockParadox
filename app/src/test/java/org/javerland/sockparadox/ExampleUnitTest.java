package org.javerland.sockparadox;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Basic unit tests for Sock Paradox game
 * 
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testPackageNameFormat() {
        String packageName = "org.javerland.sockparadox";
        assertTrue(packageName.contains("sockparadox"));
        assertTrue(packageName.startsWith("org.javerland"));
    }

    @Test
    public void testStringConcatenation() {
        String result = "Sock" + "Paradox";
        assertEquals("SockParadox", result);
    }

    @Test
    public void testBooleanLogic() {
        boolean gameStarted = true;
        boolean gameEnded = false;
        assertTrue(gameStarted && !gameEnded);
    }

    @Test
    public void testArraySize() {
        String[] rooms = {"BEDROOM", "HALLWAY", "BATHROOM", "KITCHEN", "LIVING_ROOM", "BALCONY"};
        assertEquals(6, rooms.length);
    }

    @Test
    public void testNullCheck() {
        String nullString = null;
        assertNull(nullString);
        
        String nonNullString = "test";
        assertNotNull(nonNullString);
    }
}
