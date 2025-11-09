package org.javerland.sockparadox.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit tests for ObjectDefinition model class
 */
public class ObjectDefinitionTest {

    private ObjectDefinition objectDefinition;

    @Before
    public void setUp() {
        objectDefinition = new ObjectDefinition();
    }

    @Test
    public void testObjectCode() {
        assertNull(objectDefinition.getCode());
    }

    @Test
    public void testObjectNameKey() {
        assertNull(objectDefinition.getNameKey());
    }

    @Test
    public void testObjectDescriptionKey() {
        assertNull(objectDefinition.getDescriptionKey());
    }

    @Test
    public void testObjectDefinitionNotNull() {
        assertNotNull(objectDefinition);
    }
}
