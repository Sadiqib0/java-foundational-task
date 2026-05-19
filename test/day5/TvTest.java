import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TvTest {

    private Tv tv;

    @BeforeEach
    void setUp() {
        tv = new Tv();
    }

    @Test
    @DisplayName("TV can be turned on")
    void testThatTvCanBeTurnedOn() {
        assertFalse(tv.isOn());
        tv.turnOn();
        assertTrue(tv.isOn());
    }

    @Test
    @DisplayName("TV can be turned off")
    void testThatTvCanBeTurnedOff() {
        tv.turnOn();
        tv.turnOff();
        assertFalse(tv.isOn());
    }

    @Test
    @DisplayName("Channel changes from 5 to 1 (Wraparound)")
    void testChannelWraparoundFromFiveToOne() {
        tv.turnOn();
        tv.setChannel(5);
        tv.nextChannel();
        assertEquals(1, tv.getChannel());
    }

    @Test
    @DisplayName("Channel changes from 1 to 5 (Wraparound)")
    void testChannelWraparoundFromOneToFive() {
        tv.turnOn();
        tv.setChannel(1);
        tv.previousChannel();
        assertEquals(5, tv.getChannel());
    }

    @Test
    @DisplayName("Volume increases by 1")
    void testVolumeIncrease() {
        tv.turnOn();
        int initialVolume = tv.getVolume();
        tv.increaseVolume();
        assertEquals(initialVolume + 1, tv.getVolume());
    }

    @Test
    @DisplayName("Volume cannot exceed 100")
    void testVolumeMaxLimit() {
        tv.turnOn();
        tv.setVolume(100);
        tv.increaseVolume();
        assertEquals(100, tv.getVolume());
    }

    @Test
    @DisplayName("Volume cannot go below 0")
    void testVolumeMinLimit() {
        tv.turnOn();
        tv.setVolume(0);
        tv.decreaseVolume();
        assertEquals(0, tv.getVolume());
    }

    @Test
    @DisplayName("Settings cannot change while TV is off")
    void testSettingsCannotChangeWhenOff() {
        assertFalse(tv.isOn());
        tv.setChannel(3);
        tv.increaseVolume();
        assertEquals(1, tv.getChannel()); // Remains default
        assertEquals(10, tv.getVolume()); // Remains default
    }
}