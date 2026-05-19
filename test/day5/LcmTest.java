import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
public class LcmTest {

    @Test
    @DisplayName("When both input are positive")
    void testStandardLCM() {
        assertEquals(36, Lcm.findLCM(12, 18));
    }
    @Test
    @DisplayName("When 1 input is 0 or both are 0")
    void testForZero(){
        assertEquals(0, Lcm.findLCM(10, 0));
        assertEquals(0, Lcm.findLCM(0, 10));
        assertEquals(0, Lcm.findLCM(0, 0));
    }
    @Test
    @DisplayName("When 1 or both inputs are negative")
    void testNegativeInput() {
        assertEquals(36, Lcm.findLCM(-12, 18));
        assertEquals(36, Lcm.findLCM(12, -18));
        assertEquals(36, Lcm.findLCM(-12, -18));
    }
    @Test
    @DisplayName("When multiple numbers are enter")
    void testMultipleIntegers() {
       assertEquals(72,Lcm.findLCM(12,18,24));
    }

}