import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class WordTransferTest {

        @Test
        void testSixThousand() {
            String result = WordTransfer.sendMoney("Alice", "Bob", 6000);
            assertEquals("Six Thousand", result, "6000 should convert correctly");
        }

        @Test
        void testOneMillion() {
            String result = WordTransfer.sendMoney("Alice", "Bob", 1000000);
            assertEquals("One Million", result, "The limit 1,000,000 should work");
        }

        @Test
        void testComplexNumber() {
            String result = WordTransfer.sendMoney("Alice", "Bob", 1250);
            assertEquals("One Thousand Two Hundred Fifty", result);
        }

        @Test
        void testOverLimit() {
            String result = WordTransfer.sendMoney("Alice", "Bob", 1000001);
            assertEquals("Error", result, "Anything over 1 million should return Error");
        }

        @Test
        void testZero() {
            String result = WordTransfer.sendMoney("Alice", "Bob", 0);
            assertEquals("Zero", result);
        }
    }
