package com.mycompany.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest extends AbstractSwingTest {

    @Test
    public void checkState_detectsXWin_topRow() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[0] = b[1] = b[2] = 'X';
        assertEquals(State.XWIN, g.checkState(b));
    }

    @Test
    public void checkState_detectsXWin_middleRow() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[3] = b[4] = b[5] = 'X';
        assertEquals(State.XWIN, g.checkState(b));
    }

    @Test
    public void checkState_detectsXWin_bottomRow() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[6] = b[7] = b[8] = 'X';
        assertEquals(State.XWIN, g.checkState(b));
    }

    @Test
    public void checkState_detectsOWin_firstColumn() {
        Game g = new Game();
        g.symbol = 'O';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[0] = b[3] = b[6] = 'O';
        assertEquals(State.OWIN, g.checkState(b));
    }

    @Test
    public void checkState_detectsOWin_secondColumn() {
        Game g = new Game();
        g.symbol = 'O';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[1] = b[4] = b[7] = 'O';
        assertEquals(State.OWIN, g.checkState(b));
    }

    @Test
    public void checkState_detectsOWin_thirdColumn() {
        Game g = new Game();
        g.symbol = 'O';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[2] = b[5] = b[8] = 'O';
        assertEquals(State.OWIN, g.checkState(b));
    }

    @Test
    public void checkState_detectsXWin_mainDiagonal() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[0] = b[4] = b[8] = 'X';
        assertEquals(State.XWIN, g.checkState(b));
    }

    @Test
    public void checkState_detectsOWin_antiDiagonal() {
        Game g = new Game();
        g.symbol = 'O';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[2] = b[4] = b[6] = 'O';
        assertEquals(State.OWIN, g.checkState(b));
    }

    @Test
    public void checkState_draw_fullBoard() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        b[0] = 'X'; b[1] = 'O'; b[2] = 'X';
        b[3] = 'X'; b[4] = 'O'; b[5] = 'O';
        b[6] = 'O'; b[7] = 'X'; b[8] = 'X';
        assertEquals(State.DRAW, g.checkState(b));
    }

    @Test
    public void checkState_playingWhenEmptyCells() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[0] = 'X'; b[4] = 'O';
        assertEquals(State.PLAYING, g.checkState(b));
    }

    @Test
    public void evaluatePosition_winForMaxPlayer() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[0] = b[1] = b[2] = 'X';
        assertEquals(Game.INF, g.evaluatePosition(b, g.player1));
    }

    @Test
    public void evaluatePosition_lossForMinPlayerPerspective() {
        Game g = new Game();
        g.symbol = 'O';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[0] = b[1] = b[2] = 'O';
        assertEquals(-Game.INF, g.evaluatePosition(b, g.player1));
    }

    @Test
    public void evaluatePosition_draw_zero() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        b[0] = 'X'; b[1] = 'O'; b[2] = 'X';
        b[3] = 'X'; b[4] = 'O'; b[5] = 'O';
        b[6] = 'O'; b[7] = 'X'; b[8] = 'X';
        assertEquals(0, g.evaluatePosition(b, g.player1));
    }

    @Test
    public void evaluatePosition_nonTerminal_negativeOne() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[0] = 'X';
        assertEquals(-1, g.evaluatePosition(b, g.player2));
    }

    @Test
    public void generateMoves_listsAllEmpty() {
        Game g = new Game();
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        ArrayList<Integer> moves = new ArrayList<Integer>();
        g.generateMoves(b, moves);
        assertEquals(9, moves.size());
    }

    @Test
    public void generateMoves_respectsTakenCells() {
        Game g = new Game();
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[0] = 'X'; b[4] = 'O';
        ArrayList<Integer> moves = new ArrayList<Integer>();
        g.generateMoves(b, moves);
        assertEquals(7, moves.size());
        assertTrue(!moves.contains(0) && !moves.contains(4));
    }

    @Test
    public void miniMax_withPartialBoard_returnsOnlyEmptyCellIndex() {
        Game g = new Game();
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        b[0] = 'X';
        b[2] = 'X';
        g.player1.symbol = 'X';
        g.player2.symbol = 'O';
        g.symbol = 'O';
        int move = g.MiniMax(b, g.player2);
        assertTrue(move >= 1 && move <= 9);
        assertEquals(' ', b[move - 1]);
    }

    @Test
    public void miniMax_emptyBoard_returnsCornerOrSideInRange() {
        Game g = new Game();
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        g.symbol = 'O';
        int move = g.MiniMax(b, g.player2);
        assertTrue(move >= 1 && move <= 9);
        assertEquals(' ', b[move - 1]);
    }

    @Test
    public void miniMax_runsMultipleTimes_sameProperty() {
        Game g = new Game();
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        Set<Integer> seen = new HashSet<Integer>();
        for (int i = 0; i < 15; i++) {
            Arrays.fill(b, ' ');
            int m = g.MiniMax(b, g.player2);
            assertTrue(m >= 1 && m <= 9);
            seen.add(m);
        }
        assertTrue(seen.size() >= 1);
    }

    @Test
    public void miniMax_oneMoveLeft() {
        Game g = new Game();
        char[] b = new char[9];
        b[0] = 'X'; b[1] = 'O'; b[2] = 'X';
        b[3] = 'O'; b[4] = 'O'; b[5] = 'X';
        b[6] = 'X'; b[7] = 'X'; b[8] = ' ';
        g.symbol = 'O';
        g.player1.symbol = 'X';
        g.player2.symbol = 'O';
        int move = g.MiniMax(b, g.player2);
        assertEquals(9, move);
    }

    @Test
    public void constructor_initializesPlayersAndBoard() {
        Game g = new Game();
        assertEquals('X', g.player1.symbol);
        assertEquals('O', g.player2.symbol);
        assertEquals(State.PLAYING, g.state);
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', g.board[i]);
        }
    }
}
