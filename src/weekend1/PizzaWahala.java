import java.util.Scanner;

public class PizzaWahala {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        System.out.println("--- Welcome to Iya Moses Pizza joint Ajegunle Pizza ---");

        System.out.println("How many people are coming to the party:");
        int numPeople = inputReader.nextInt();

        String pizzaMenu = """
            Choose your pizza type:
            1 - Sapa size (4 slices, ₦2500)
            2 - Small Money (6 slices, ₦2900)
            3 - Big boys (8 slices, ₦4000)
            4 - Odogwu (12 slices, ₦5200)
            Enter number (1-4):\s""";

        System.out.print(pizzaMenu);
        int choice = inputReader.nextInt();

        int slicesPerBox = 0;
        int pricePerBox = 0;

        switch (choice) {
            case 1 -> { slicesPerBox = 4; pricePerBox = 2500; }
            case 2 -> { slicesPerBox = 6; pricePerBox = 2900; }
            case 3 -> { slicesPerBox = 8; pricePerBox = 4000; }
            case 4 -> { slicesPerBox = 12; pricePerBox = 5200; }
            default -> {
                System.out.println("Wrong Menu selection!");
                return;
            }
        }
        int[] results = calculateResults(numPeople, slicesPerBox, pricePerBox);

        System.out.println("Number of pizza boxes to buy = " + results[0] + " boxes");
        System.out.println("Number of slices after serving = " + results[1] + " slices");
        System.out.println("Total Price = ₦" + results[2]);
    }

    public static int[] calculateResults(int numPeople, int slicesPerBox, int pricePerBox) {
        int boxes = (numPeople + slicesPerBox - 1) / slicesPerBox;
        int totalSlices = boxes * slicesPerBox;
        int leftover = totalSlices - numPeople;
        int totalMoney = boxes * pricePerBox;

        return new int[]{boxes, leftover, totalMoney};
    }
}