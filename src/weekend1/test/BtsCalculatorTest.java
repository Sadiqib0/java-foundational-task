import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BtsCalculatorTest {

    @Test
     void lessThan_50_Delivery_shouldUse160PerParcel() {
        int wage = BtsCalculator.calculateWage(25);
        assertEquals(9000, wage);
    }

    @Test
    void exactly_50_Deliveries_shouldUse200PerParcel() {
        int wage = BtsCalculator.calculateWage(50);
        assertEquals(15000, wage);
    }

    @Test
    void exactly_59_Deliveries_() {
        int wage = BtsCalculator.calculateWage(59);
        assertEquals(16800, wage);
    }

    @Test
    void exactly_60_Deliveries_shouldUse250PerParcel() {
        int wage = BtsCalculator.calculateWage(60);
        assertEquals(20000, wage);
    }

    @Test
    void exactly_69_Deliveries_shouldStillBeInThe60to69Range() {
        int wage = BtsCalculator.calculateWage(69);
        assertEquals(22250, wage);
    }

    @Test
    void exactly_70_Deliveries_shouldUse500PerParcel_shouldUse500PerParcel() {
        int wage = BtsCalculator.calculateWage(70);
        assertEquals(40000, wage);
    }


    @Test
    void exactly_100_Deliveries_shouldUse500PerParcel() {
        int wage = BtsCalculator.calculateWage(100);
        assertEquals(55000, wage);
    }

    @Test
    void negativeDeliveries_shouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BtsCalculator.calculateWage(-1)
        );
        assertTrue(exception.getMessage().contains("negative"));
    }

    @Test
    void deliveriesExceeding100_shouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BtsCalculator.calculateWage(101)
        );
        assertTrue(exception.getMessage().contains("exceed"));
    }
}