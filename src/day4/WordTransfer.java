public class WordTransfer {

    public static String sendMoney(String userA, String userB, int amount) {

        if (amount < 0 || amount > 1000000) {
            return "Error";
        }
        String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        String amountInWords = "";

        if (amount == 0) {
            amountInWords = "Zero";
        } else if (amount == 1000000) {
            amountInWords = "One Million";
        } else {
            int temp = amount;
            if (temp / 1000 > 0) {
                int thousands = temp / 1000;
                if (thousands / 100 > 0) amountInWords += units[thousands / 100] + " Hundred ";
                if (thousands % 100 < 20) {
                    amountInWords += units[thousands % 100];
                } else {
                    amountInWords += tens[(thousands % 100) / 10] + " " + units[thousands % 10];
                }
                amountInWords += " Thousand ";
                temp %= 1000;
            }

            if (temp / 100 > 0) {
                amountInWords += units[temp / 100] + " Hundred ";
                temp %= 100;
            }

            if (temp > 0) {
                if (temp < 20) {
                    amountInWords += units[temp];
                } else {
                    amountInWords += tens[temp / 10] + " " + units[temp % 10];
                }
            }
        }
        System.out.println(userA + " sent " + amount + " (" + amountInWords.trim() + ") to " + userB);
        return amountInWords.trim();
    }

    public static void main(String[] args) {
    }
}