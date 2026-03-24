import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
    }

    @Test
    void shouldAddOneItemToCart() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        assertEquals(1, checkoutService.getCartSize());
    }

    @Test
    void shouldAddMultipleItemsToCart() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        checkoutService.addItem("Rice", 2, 550.00);
        assertEquals(2, checkoutService.getCartSize());
    }

    @Test
    void shouldThrowExceptionWhenItemNameIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.addItem(null, 2, 2100.00));
    }

    @Test
    void shouldThrowExceptionWhenItemNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.addItem("", 2, 2100.00));
    }

    @Test
    void shouldThrowExceptionWhenQuantityIsZero() {
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.addItem("Parfait", 0, 2100.00));
    }

    @Test
    void shouldThrowExceptionWhenQuantityIsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.addItem("Parfait", -1, 2100.00));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsZero() {
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.addItem("Parfait", 2, 0.00));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.addItem("Parfait", 2, -500.00));
    }

    @Test
    void shouldCalculateCorrectSubTotalForOneItem() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        assertEquals(4200.00, checkoutService.getSubTotal(), 0.01);
    }

    @Test
    void shouldCalculateCorrectSubTotalForMultipleItems() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        checkoutService.addItem("Rice", 2, 550.00);
        assertEquals(5300.00, checkoutService.getSubTotal(), 0.01);
    }

    @Test
    void shouldReturnZeroSubTotalWhenCartIsEmpty() {
        assertEquals(0.00, checkoutService.getSubTotal(), 0.01);
    }

    @Test
    void shouldCalculateCorrectDiscount() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        checkoutService.addItem("Rice", 2, 550.00);
        assertEquals(424.00, checkoutService.getDiscount(8), 0.01);
    }

    @Test
    void shouldReturnZeroDiscountWhenDiscountPercentageIsZero() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        assertEquals(0.00, checkoutService.getDiscount(0), 0.01);
    }

    @Test
    void shouldThrowExceptionWhenDiscountIsNegative() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.getDiscount(-5));
    }

    @Test
    void shouldThrowExceptionWhenDiscountIsGreaterThan100() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.getDiscount(101));
    }

    @Test
    void shouldReturnZeroDiscountWhenCartIsEmpty() {
        assertEquals(0.00, checkoutService.getDiscount(8), 0.01);
    }

    @Test
    void shouldCalculateCorrectVAT() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        checkoutService.addItem("Rice", 2, 550.00);
        // After 8% discount: 5300 - 424 = 4876
        // 17.5% VAT on 4876 = 853.30
        assertEquals(853.30, checkoutService.getVAT(8), 0.01);
    }

    @Test
    void shouldReturnZeroVATWhenCartIsEmpty() {
        assertEquals(0.00, checkoutService.getVAT(), 0.01);
    }

    @Test
    void shouldCalculateCorrectBillTotal() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        checkoutService.addItem("Rice", 2, 550.00);
        // 5300 - 424 + 853.30 = 5729.30
        assertEquals(5729.30, checkoutService.getBillTotal(8), 0.01);
    }

    @Test
    void shouldCalculateBillTotalWithNoDiscount() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        checkoutService.addItem("Rice", 2, 550.00);
        // 5300 + 927.50 = 6227.50
        assertEquals(6227.50, checkoutService.getBillTotal(0), 0.01);
    }

    @Test
    void shouldThrowExceptionWhenBillTotalCalledOnEmptyCart() {
        assertThrows(IllegalStateException.class, () ->
                checkoutService.getBillTotal(8));
    }

    @Test
    void shouldCalculateCorrectBalanceAfterPayment() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        checkoutService.addItem("Rice", 2, 550.00);
        // Bill total = 5729.30, paid 6000 → balance = 270.70
        assertEquals(270.70, checkoutService.getBalance(6000.00, 8), 0.01);
    }

    @Test
    void shouldReturnZeroBalanceWhenExactAmountIsPaid() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        checkoutService.addItem("Rice", 2, 550.00);
        assertEquals(0.00, checkoutService.getBalance(5729.30, 8), 0.01);
    }

    @Test
    void shouldThrowExceptionWhenAmountPaidIsLessThanBillTotal() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        checkoutService.addItem("Rice", 2, 550.00);
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.getBalance(5000.00, 8));
    }

    @Test
    void shouldThrowExceptionWhenAmountPaidIsZero() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.getBalance(0.00, 8));
    }

    @Test
    void shouldThrowExceptionWhenAmountPaidIsNegative() {
        checkoutService.addItem("Parfait", 2, 2100.00);
        assertThrows(IllegalArgumentException.class, () ->
                checkoutService.getBalance(-100.00, 8));
    }
}