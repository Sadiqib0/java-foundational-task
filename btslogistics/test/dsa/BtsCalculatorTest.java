package dsa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BtsCalculatorTest {

    @Test
    void lessThan_50_Delivery() {
        int wage = BtsCalculator.calculateWage(25);
        assertEquals(9000, wage);
    }
    @Test
    void exactly_50_Deliveries() {
        int wage = BtsCalculator.calculateWage(50);
        assertEquals(15000, wage);
    }

    @Test
    void exactly_59_Deliveries() {
        int wage = BtsCalculator.calculateWage(59);
        assertEquals(16800, wage);
    }

    @Test
    void exactly_60_Deliveries() {
        int wage = BtsCalculator.calculateWage(60);
        assertEquals(20000, wage);
    }

    @Test
    void exactly_69_Deliveries() {
        int wage = BtsCalculator.calculateWage(69);
        assertEquals(22250, wage);
    }

    @Test
    void exactly_70_Deliveries() {
        int wage = BtsCalculator.calculateWage(70);
        assertEquals(40000, wage);
    }


    @Test
    void exactly_100_Deliveries() {
        int wage = BtsCalculator.calculateWage(100);
        assertEquals(55000, wage);
    }
}