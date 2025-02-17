import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int digit = scan.nextInt();
        if (digit <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        boolean prime = true;
        int counter = 1;
        for (int i = 2; i * i <= digit; i++, counter++) {
            if (digit % i == 0) {
                prime = false;
                break;
            }
        }
        if (prime) {
            System.out.println("true " + counter);
        } else {
            System.out.println("false " + counter);
        }
    }
}
