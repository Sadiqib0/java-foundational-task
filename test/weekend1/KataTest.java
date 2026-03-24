import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KataTest {

    private Kata kata;

    @BeforeEach
    void setUp() {
        // Since the methods are not static, we create a new object before each test
        kata = new Kata();
    }

    @Test
    @DisplayName("Check if a number is even")
    void testIsEven() {
        assertTrue(kata.isEven(10));
        assertFalse(kata.isEven(7));
    }

    @Test
    @DisplayName("Check if a number is prime")
    void testIsPrimeNumber() {
        assertTrue(kata.isPrimeNumber(7));
        assertTrue(kata.isPrimeNumber(13));
        assertFalse(kata.isPrimeNumber(4));
        assertFalse(kata.isPrimeNumber(1));
    }

    @Test
    @DisplayName("Subtract two numbers and return absolute difference")
    void testSubtract() {
        assertEquals(5, kata.subtract(10, 5));
        assertEquals(5, kata.subtract(5, 10));
    }

    @Test
    @DisplayName("Divide two numbers with float precision")
    void testDivide() {
        assertEquals(2.5f, kata.divide(5, 2));
        assertEquals(0.0f, kata.divide(5, 0));
    }

    @Test
    @DisplayName("Count factors of a number")
    void testFactorOf() {
        assertEquals(4, kata.factorOf(6));
        assertEquals(4, kata.factorOf(10));
    }

    @Test
    @DisplayName("Check if a number is a perfect square")
    void testIsSquare() {
        assertTrue(kata.isSquare(16));
        assertTrue(kata.isSquare(25));
        assertFalse(kata.isSquare(20));
        assertFalse(kata.isSquare(-4));
    }

    @Test
    @DisplayName("Check if a number is a palindrome")
    void testIsPalindrome() {
        assertTrue(kata.isPalindrome(121));
        assertTrue(kata.isPalindrome(444));
        assertFalse(kata.isPalindrome(123));
    }

    @Test
    @DisplayName("Calculate factorial of a number")
    void testFactorialOf() {
        assertEquals(120, kata.factorialOf(5)); // 5*4*3*2*1
        assertEquals(1, kata.factorialOf(0));
        assertEquals(0, kata.factorialOf(-1));
    }

    @Test
    @DisplayName("Calculate the square of a number")
    void testSquareOf() {
        assertEquals(25L, kata.squareOf(5));
        assertEquals(100L, kata.squareOf(-10));
    }
}