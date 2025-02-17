import java.util.Scanner;


public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while (!str.equals("42")) {
            Checker.check(str);
            str = scanner.nextLine();
        }
    }
}
