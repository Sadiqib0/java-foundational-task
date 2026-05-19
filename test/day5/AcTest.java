import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ACTest {

    private Ac ac;

    @BeforeEach
    void setUp() {
        ac = new Ac();
    }

    @Test
    @DisplayName("AC is off by default")
    void testAcIsOffByDefault() {
        assertFalse(ac.isOn());
    }

    @Test
    @DisplayName("AC can be turned on")
    void testAcCanBeTurnedOn() {
        ac.turnOn();
        assertTrue(ac.isOn());
    }

    @Test
    @DisplayName("AC can be turned off")
    void testAcCanBeTurnedOff() {
        ac.turnOn();
        ac.turnOff();
        assertFalse(ac.isOn());
    }

    @Test
    @DisplayName("Increase temperature increments by 1")
    void testIncreaseTemperature() {
        ac.turnOn();
        ac.setTemperature(16);
        ac.increaseTemperature();
        assertEquals(17, ac.getTemperature());
    }

    @Test
    @DisplayName("Decrease temperature decrements by 1")
    void testDecreaseTemperature() {
        ac.turnOn();
        ac.setTemperature(25);
        ac.decreaseTemperature();
        assertEquals(24, ac.getTemperature());
    }

    @Test
    @DisplayName("Temperature cannot go above 30")
    void testTemperatureUpperLimit() {
        ac.turnOn();
        ac.setTemperature(30);
        ac.increaseTemperature();
        assertEquals(30, ac.getTemperature());
    }

    @Test
    @DisplayName("Temperature cannot go below 16")
    void testTemperatureLowerLimit() {
        ac.turnOn();
        ac.setTemperature(16);
        ac.decreaseTemperature();
        assertEquals(16, ac.getTemperature());
    }

    @Test
    @DisplayName("Temperature cannot be changed if AC is off")
    void testTemperatureChangeWhileOff() {
        assertFalse(ac.isOn());
        ac.setTemperature(20); // Attempt to set
        ac.increaseTemperature(); // Attempt to increase
        assertEquals(16, ac.getTemperature()); // Should remain at default initial state
    }
}