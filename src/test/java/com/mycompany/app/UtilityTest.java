package com.mycompany.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UtilityTest {

    @Test
    public void printCharBoard_writesToStdout() {
        PrintStream orig = System.out;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buf));
        try {
            char[] b = new char[9];
            Arrays.fill(b, ' ');
            b[0] = 'X';
            Utility.print(b);
        } finally {
            System.setOut(orig);
        }
        String s = buf.toString();
        assertTrue(s.contains("X"));
    }

    @Test
    public void printIntBoard_writesToStdout() {
        PrintStream orig = System.out;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buf));
        try {
            int[] a = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
            Utility.print(a);
        } finally {
            System.setOut(orig);
        }
        assertTrue(buf.toString().length() > 0);
    }

    @Test
    public void printMoveList_writesToStdout() {
        PrintStream orig = System.out;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buf));
        try {
            ArrayList<Integer> m = new ArrayList<Integer>();
            m.add(1);
            m.add(3);
            Utility.print(m);
        } finally {
            System.setOut(orig);
        }
        assertTrue(buf.toString().contains("1"));
    }
}
