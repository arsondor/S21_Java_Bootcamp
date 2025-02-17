import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        for (int i = 1; i <= 18; i++) {
            String week = scan.nextLine();
            if (week.equals("42")) break;
            if (!week.equals("Week " + i)) {
                error();
            }
            int digit = 9, scan_digit = 0;
            for (int j = 0; j < 5; j++) {
                if (!scan.hasNextInt()) {
                    error();
                }
                scan_digit = scan.nextInt();
                if ((scan_digit < 1 || scan_digit > 9)) {
                    error();
                }
                digit = Math.min(scan_digit, digit);
            }
                concat(digit, str, i);
            scan.nextLine();
        }

        System.out.print(str);
    }

    private static void concat(int digit, StringBuilder str, int num) {
        str.append("Weak ");
        str.append(num);
        str.append(' ');
        str.append("=".repeat(Math.max(0, digit)));
        str.append(">\n");
    }

    private static void error() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

}

