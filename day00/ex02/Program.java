import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int digit = 0;
        int count = 0;
        while (digit != 42) {
            digit = scan.nextInt();
            if (digit < 2) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            if (sum_prime(digit)) count++;
        }
        System.out.println("Count of coffee-request - " + count);
    }

    private static boolean sum_prime(int digit) {
        boolean prime = true;
        int sum = 0;
        while (digit != 0) {
            sum += digit % 10;
            digit /= 10;
        }
        for (int i = 2; i * i <= sum; i++) {
            if (sum % i == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }
}