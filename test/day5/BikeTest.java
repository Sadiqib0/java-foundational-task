import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikeTest {

    private Bike bike;

    @BeforeEach
    void setUp() {
        bike = new Bike();
    }

    @Test
    @DisplayName("Bike can be turned on")
    void testThatBikeCanBeTurnedOn() {
        assertFalse(bike.isOn());
        bike.turnOn();
        assertTrue(bike.isOn());
    }

    @Test
    @DisplayName("Bike can be turned off")
    void testThatBikeCanBeTurnedOff() {
        bike.turnOn();
        assertTrue(bike.isOn());
        bike.turnOff();
        assertFalse(bike.isOn());
    }

    @Test
    @DisplayName("Bike accelerates by 1 in Gear 1")
    void testThatBikeAcceleratesByOneInGearOne() {
        bike.turnOn();
        bike.setSpeed(15);
        assertEquals(1, bike.getGear());
        bike.accelerate();
        assertEquals(16, bike.getSpeed());
    }

    @Test
    @DisplayName("Bike accelerates by 2 in Gear 2")
    void testThatBikeAcceleratesByTwoInGearTwo() {
        bike.turnOn();
        bike.setSpeed(24);
        assertEquals(2, bike.getGear());
        bike.accelerate();
        assertEquals(26, bike.getSpeed());
    }

    @Test
    @DisplayName("Bike accelerates by 3 in Gear 3")
    void testThatBikeAcceleratesByThreeInGearThree() {
        bike.turnOn();
        bike.setSpeed(35);
        assertEquals(3, bike.getGear());
        bike.accelerate();
        assertEquals(38, bike.getSpeed());
    }

    @Test
    @DisplayName("Bike accelerates by 4 in Gear 4")
    void testThatBikeAcceleratesByFourInGearFour() {
        bike.turnOn();
        bike.setSpeed(44);
        assertEquals(4, bike.getGear());
        bike.accelerate();
        assertEquals(48, bike.getSpeed());
    }


    @Test
    @DisplayName("Bike decelerates by 1 in Gear 1")
    void testThatBikeDeceleratesByOneInGearOne() {
        bike.turnOn();
        bike.setSpeed(15);
        bike.decelerate();
        assertEquals(14, bike.getSpeed());
    }

    @Test
    @DisplayName("Bike decelerates by 2 in Gear 2")
    void testThatBikeDeceleratesByTwoInGearTwo() {
        bike.turnOn();
        bike.setSpeed(24);
        bike.decelerate();
        assertEquals(22, bike.getSpeed());
    }

    @Test
    @DisplayName("Bike decelerates by 3 in Gear 3")
    void testThatBikeDeceleratesByThreeInGearThree() {
        bike.turnOn();
        bike.setSpeed(35);
        bike.decelerate();
        assertEquals(32, bike.getSpeed());
    }

    @Test
    @DisplayName("Bike decelerates by 4 in Gear 4")
    void testThatBikeDeceleratesByFourInGearFour() {
        bike.turnOn();
        bike.setSpeed(44);
        bike.decelerate();
        assertEquals(40, bike.getSpeed());
    }
    @Test
    @DisplayName("Gear changes from 1 to 2 when speed transitions from 20 to 21")
    void testAutomaticGearShiftFromOneToTwo() {
        bike.turnOn();
        bike.setSpeed(20);
        assertEquals(1, bike.getGear());
        bike.accelerate();
        assertEquals(21, bike.getSpeed());
        assertEquals(2, bike.getGear());
    }

    @Test
    @DisplayName("Gear changes from 2 to 3 when speed crosses 30")
    void testAutomaticGearShiftFromTwoToThree() {
        bike.turnOn();
        bike.setSpeed(30);
        assertEquals(2, bike.getGear());
        bike.accelerate();
        assertEquals(32, bike.getSpeed());
        assertEquals(3, bike.getGear()); //
    }

    @Test
    @DisplayName("Gear changes from 3 to 4 when speed crosses 40")
    void testAutomaticGearShiftFromThreeToFour() {
        bike.turnOn();
        bike.setSpeed(40);
        assertEquals(3, bike.getGear());
        bike.accelerate();
        assertEquals(43, bike.getSpeed());
        assertEquals(4, bike.getGear()); //
    }

    @Test
    @DisplayName("Gear changes downwards when decelerating across boundaries")
    void testAutomaticGearShiftDownwards() {
        bike.turnOn();
        bike.setSpeed(22);
        bike.decelerate();
        assertEquals(20, bike.getSpeed());
        assertEquals(1, bike.getGear());
    }

    @Test
    @DisplayName("Speed cannot drop below zero")
    void testThatSpeedCannotBeNegative() {
        bike.turnOn();
        bike.setSpeed(0);
        bike.decelerate();
        assertEquals(0, bike.getSpeed());
    }
}
