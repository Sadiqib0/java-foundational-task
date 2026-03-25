import java.util.ArrayList;
import java.util.List;

public class NumberFactors {

    public static List<Integer> getFactors(int number) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                divisors.add(i);
            }
        }
        return divisors;
    }
    public static List<Integer> getReverseFactors(int number) {
        List<Integer> primeFactors = new ArrayList<>();
        int temp = number;

        if (temp < 2) return primeFactors;

        while (temp % 2 == 0) {
            primeFactors.add(2);
            temp /= 2;
        }
        for (int i = 3; i * i <= temp; i += 2) {
            while (temp % i == 0) {
                primeFactors.add(i);
                temp /= i;
            }
        }
        if (temp > 1) {
            primeFactors.add(temp);
        }
        return primeFactors;
    }

    public static void main(String[] args) {

    }
}