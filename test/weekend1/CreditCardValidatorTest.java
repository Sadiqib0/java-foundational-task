import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardValidatorTest {

    void testGetCardType(String cardNumber, String expectedType) {
        assertEquals(expectedType, CreditCardValidator.getCardType(cardNumber));
    }

    @Test
    void testIsValidCard_ValidVisa() {
        String validVisa = "5334771146311446";
        assertTrue(CreditCardValidator.isValidCard(validVisa));
    }

    @Test
    void testIsValidCard_InvalidChecksum() {
        String invalidVisa = "4388576018402627";
        assertFalse(CreditCardValidator.isValidCard(invalidVisa));
    }

    @Test
    void testIsValidCard_InvalidLength() {
        assertFalse(CreditCardValidator.isValidCard("4123"));
        assertFalse(CreditCardValidator.isValidCard("41234567890123456789"));
    }

    @Test
    void testLuhnLogic_DoubleDigitSum() {
        String cardTriggeringDoubleSum = "5334771146311446";
        assertTrue(CreditCardValidator.isValidCard(cardTriggeringDoubleSum));
    }
}