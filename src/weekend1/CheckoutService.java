public class CheckoutService {

    private static final double VAT_RATE = 17.5;
    private static final int MAX_ITEMS = 100;

    private final String[] itemNames;
    private final int[] itemQuantities;
    private final double[] itemPrices;
    private int itemCount;

    private String customerName;
    private String cashierName;

    public CheckoutService() {
        itemNames      = new String[MAX_ITEMS];
        itemQuantities = new int[MAX_ITEMS];
        itemPrices     = new double[MAX_ITEMS];
        itemCount      = 0;
    }

    public void addItem(String name, int quantity, double pricePerUnit) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (pricePerUnit <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if (itemCount >= MAX_ITEMS) {
            throw new IllegalStateException("Cart is full, cannot add more items");
        }

        itemNames[itemCount]      = name;
        itemQuantities[itemCount] = quantity;
        itemPrices[itemCount]     = pricePerUnit;
        itemCount++;
    }

    public double getSubTotal() {
        double subTotal = 0.0;
        for (int i = 0; i < itemCount; i++) {
            subTotal += itemQuantities[i] * itemPrices[i];
        }
        return subTotal;
    }

    public double getDiscount(double discountPercentage) {
        if (discountPercentage < 0) {
            throw new IllegalArgumentException("Discount cannot be negative");
        }
        if (discountPercentage > 100) {
            throw new IllegalArgumentException("Discount cannot be greater than 100%");
        }
        return (discountPercentage / 100.0) * getSubTotal();
    }

    public double getVAT(double discountPercentage) {
        double amountAfterDiscount = getSubTotal() - getDiscount(discountPercentage);
        return (VAT_RATE / 100.0) * amountAfterDiscount;
    }

    public double getVAT() {
        return getVAT(0.0);
    }

    public double getBillTotal(double discountPercentage) {
        if (itemCount == 0) {
            throw new IllegalStateException("Cannot calculate bill total on an empty cart");
        }

        double subTotal = getSubTotal();
        double discount = getDiscount(discountPercentage);
        double vat      = getVAT(discountPercentage);

        return subTotal - discount + vat;
    }

    public double getBalance(double amountPaid, double discountPercentage) {
        if (amountPaid <= 0) {
            throw new IllegalArgumentException("Amount paid must be greater than zero");
        }

        double billTotal = getBillTotal(discountPercentage);

        if (amountPaid < billTotal) {
            throw new IllegalArgumentException(
                    "Amount paid (" + amountPaid + ") is less than bill total (" + billTotal + ")"
            );
        }

        return amountPaid - billTotal;
    }

    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        this.customerName = customerName;
    }

    public void setCashierName(String cashierName) {
        if (cashierName == null || cashierName.trim().isEmpty()) {
            throw new IllegalArgumentException("Cashier name cannot be null or empty");
        }
        this.cashierName = cashierName;
    }

    public String getCustomerName() { return customerName; }
    public String getCashierName()  { return cashierName; }
    public int getCartSize()        { return itemCount; }

    public String[] getItemNames()      { return itemNames; }
    public int[] getItemQuantities()    { return itemQuantities; }
    public double[] getItemPrices()     { return itemPrices; }
}