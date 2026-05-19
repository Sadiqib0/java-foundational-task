public class BtsCalculator {

    public static void main(String[] args) {

    }
    private static final int TOTAL_PACKAGES = 100;
    private static final int BASE_PAY = 5000;

    public static int calculateWage(int successfulDeliveries) {

        if (successfulDeliveries < 0) {
            throw new IllegalArgumentException("Deliveries cannot be negative. Got: " + successfulDeliveries);
        }

        if (successfulDeliveries > TOTAL_PACKAGES) {
            throw new IllegalArgumentException("Deliveries cannot exceed " + TOTAL_PACKAGES + ". Got: " + successfulDeliveries);
        }

        int amountPerParcel = getParcelAmount(successfulDeliveries);

        return (successfulDeliveries * amountPerParcel) + BASE_PAY;
    }

    private static int getParcelAmount(int successfulDeliveries) {
        double collectionRate = (successfulDeliveries / (double) TOTAL_PACKAGES) * 100;
        if (collectionRate >= 70) {
            return 500;
        } else if (collectionRate >= 60) {
            return 250;
        } else if (collectionRate >= 50) {
            return 200;
        } else {
            return 160;
        }
    }
}