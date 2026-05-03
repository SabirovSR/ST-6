package com.mycompany.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Headless AWT and quiet {@code System.out} for tests that touch Swing or verbose minimax logs.
 */
public abstract class AbstractSwingTest {

    private static final PrintStream ORIGINAL_OUT = System.out;
    private ByteArrayOutputStream muted;

    @BeforeClass
    public static void enableHeadless() {
        System.setProperty("java.awt.headless", "true");
    }

    @Before
    public void muteStdout() {
        muted = new ByteArrayOutputStream();
        System.setOut(new PrintStream(muted));
    }

    @After
    public void restoreStdout() {
        System.setOut(ORIGINAL_OUT);
    }
}
