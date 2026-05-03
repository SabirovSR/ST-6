package com.mycompany.app;

import java.awt.GridLayout;

import org.junit.Assert;
import org.junit.Test;

/**
 * Constructs the Swing panel without simulating clicks (avoids {@code JOptionPane} / {@code System.exit}).
 */
public class TicTacToePanelTest extends AbstractSwingTest {

    @Test
    public void panelConstructsAndHoldsNineCells() {
        TicTacToePanel p = new TicTacToePanel(new GridLayout(3, 3));
        Assert.assertEquals(9, p.getComponentCount());
    }
}
