package com.mycompany.app;

import java.awt.GridLayout;

import org.junit.Assert;
import org.junit.Test;

public class TicTacToeCellTest extends AbstractSwingTest {

    @Test
    public void cell_storesCoordinatesAndMarker() {
        TicTacToeCell c = new TicTacToeCell(4, 1, 2);
        Assert.assertEquals(4, c.getNum());
        Assert.assertEquals(1, c.getCol());
        Assert.assertEquals(2, c.getRow());
        Assert.assertEquals(' ', c.getMarker());
    }

    @Test
    public void setMarker_updatesTextAndMarker() {
        TicTacToeCell c = new TicTacToeCell(0, 0, 0);
        c.setMarker("X");
        Assert.assertEquals('X', c.getMarker());
        Assert.assertEquals("X", c.getText());
    }
}
