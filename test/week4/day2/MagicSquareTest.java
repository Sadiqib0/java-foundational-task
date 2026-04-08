package week4.day2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static week4.day2.MagicSquareArray.checkMagicSquare;

public class MagicSquareTest {

        @Test
        void testValid3x3MagicSquare() {
            int[][] magicSquare = {
                    {8, 1, 6},
                    {3, 5, 7},
                    {4, 9, 2}
            };
            assertTrue(checkMagicSquare(magicSquare));
        }

        @Test
        void testAnotherValidMagicSquare() {
            int[][] magicSquare = {
                    {2, 7, 6},
                    {9, 5, 1},
                    {4, 3, 8}
            };
            assertTrue(checkMagicSquare(magicSquare));
        }

        @Test
        void testInvalidMagicSquareLastWrongRowSum() {
            int[][] notMagic = {
                    {8, 1, 6},
                    {3, 5, 7},
                    {4, 9, 3}
            };
            assertFalse(checkMagicSquare(notMagic));
        }

        @Test
        void testInvalidNotSquareMatrix() {
            int[][] rectangular = {
                    {1, 2, 3},
                    {4, 5, 6}
            };
            assertFalse(checkMagicSquare(rectangular));
        }

        @Test
        void testNullMatrix() {
            assertFalse(checkMagicSquare(null));
        }

        @Test
        void testEmptyMatrix() {
            int[][] empty = new int[0][0];
            assertFalse(checkMagicSquare(empty));
        }

        @Test
        void testInvalidDiagonal() {
            int[][] brokenDiag = {
                    {8, 1, 6},
                    {3, 5, 7},
                    {4, 9, 2}
            };
            brokenDiag[2][2] = 99;
            assertFalse(checkMagicSquare(brokenDiag));
        }

        @Test
        void test4by4MagicSquare() {
            int[][] magic4by4 = {
                    {16,  3,  2, 13},
                    { 5, 10, 11,  8},
                    { 9,  6,  7, 12},
                    { 4, 15, 14,  1}
            };
            assertTrue(checkMagicSquare(magic4by4));
        }
    }
