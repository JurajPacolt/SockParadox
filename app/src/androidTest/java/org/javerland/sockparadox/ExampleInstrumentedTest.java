package org.javerland.sockparadox;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test for basic app context verification
 * This will execute on an Android device or emulator
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    
    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("org.javerland.sockparadox", appContext.getPackageName());
    }

    @Test
    public void testAppNameResourceExists() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String appName = appContext.getString(R.string.app_name);
        assertNotNull("App name should not be null", appName);
        assertFalse("App name should not be empty", appName.isEmpty());
    }

    @Test
    public void testResourcesAccessible() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertNotNull("Resources should be accessible", appContext.getResources());
    }

    @Test
    public void testPackageManagerAccessible() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PackageManager pm = appContext.getPackageManager();
        assertNotNull("PackageManager should be accessible", pm);
    }

    @Test
    public void testRawResourceExists() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        int resourceId = appContext.getResources().getIdentifier("rooms", "raw", 
                        appContext.getPackageName());
        assertTrue("rooms.json should exist in raw resources", resourceId != 0);
    }
}
