package week4.day1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static week4.day1.Divisor.divideNumbers;

public class DivisorTest {

        @Test
        void testDivision() {
            assertEquals(5, divideNumbers(10, 2));
            assertEquals(2, divideNumbers(7, 3));
            assertEquals(10, divideNumbers(10, 1));
        }

        @Test
        void testInvalidDivisorThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> {
                divideNumbers(10, 0);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                divideNumbers(10, -5);
            });
        }

    }
