package dsa;

public class BtsCalculator {

    public static void main(String[] args) {

    }

    private static final int totalPackages = 100;
    private static final int basePay = 5000;

    public static int calculateWage(int successfulDeliveries) {
        int amountPerParcel = getParcelAmount(successfulDeliveries);
        return (successfulDeliveries * amountPerParcel) + basePay;
    }

    private static int getParcelAmount(int successfulDeliveries) {
        double collectionRate = (successfulDeliveries / (double) totalPackages) * 100;
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