package org.javerland.sockparadox.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Unit tests for Npc model class
 */
public class NpcTest {

    private Npc npc;

    @Before
    public void setUp() {
        npc = new Npc();
    }

    @Test
    public void testNpcCode() {
        assertNull(npc.getCode());
    }

    @Test
    public void testNpcNameKey() {
        assertNull(npc.getNameKey());
    }

    @Test
    public void testNpcDescriptionKey() {
        assertNull(npc.getDescriptionKey());
    }

    @Test
    public void testNpcDialogues() {
        assertNull(npc.getDialogues());
    }

    @Test
    public void testNpcActions() {
        assertNull(npc.getActions());
    }

    @Test
    public void testDialogueTrigger() {
        Npc.Dialogue dialogue = new Npc.Dialogue();
        assertNull(dialogue.getTrigger());
    }

    @Test
    public void testDialogueTextKey() {
        Npc.Dialogue dialogue = new Npc.Dialogue();
        assertNull(dialogue.getTextKey());
    }

    @Test
    public void testNpcActionCommandKey() {
        Npc.NpcAction action = new Npc.NpcAction();
        assertNull(action.getCommandKey());
    }

    @Test
    public void testNpcActionConditions() {
        Npc.NpcAction action = new Npc.NpcAction();
        assertNull(action.getConditions());
    }

    @Test
    public void testNpcActionResultKey() {
        Npc.NpcAction action = new Npc.NpcAction();
        assertNull(action.getResultKey());
    }

    @Test
    public void testNpcActionEffects() {
        Npc.NpcAction action = new Npc.NpcAction();
        assertNull(action.getEffects());
    }

    @Test
    public void testNpcActionIsRepeatable_Null() {
        Npc.NpcAction action = new Npc.NpcAction();
        assertFalse(action.isRepeatable());
    }

    @Test
    public void testNpcNotNull() {
        assertNotNull(npc);
    }
}
