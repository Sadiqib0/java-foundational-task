import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class PizzaWahalaTest {

    @Test
    @DisplayName("Test Sapa Size: 5 people should need 2 boxes")
    void testSapaSize() {
        int[] result = PizzaWahala.calculateResults(5, 4, 2500);

        assertEquals(2, result[0], "Should be 2 boxes");
        assertEquals(3, result[1], "Should be 3 leftovers");
        assertEquals(5000, result[2], "Should be 5000 Naira");
    }

    @Test
    @DisplayName("Test Odogwu Size: 12 people (Perfect Fit)")
    void testOdogwuPerfectFit() {
        int[] result = PizzaWahala.calculateResults(12, 12, 5200);

        assertEquals(1, result[0]);
        assertEquals(0, result[1]);
        assertEquals(5200, result[2]);
    }

    @Test
    @DisplayName("Test Big Boys: 20 people")
    void testBigBoysLargeGroup() {
        int[] result = PizzaWahala.calculateResults(20, 8, 4000);

        assertAll("Big Boys Stats",
                () -> assertEquals(3, result[0]),
                () -> assertEquals(4, result[1]),
                () -> assertEquals(12000, result[2])
        );
    }
}