import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] str = scan.nextLine().toCharArray();
        char[] top_chars = new char[10];
        int[] top_counts = new int[10];
        int[] all_count = new int[65536];
        for (char c : str) {
            all_count[c]++;
        }
        int min = 0;
        for (int i = 65535; i >= 0; i--) {
            if (all_count[i] > min) {
                if (all_count[i] > 999) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                min = push_forw(top_chars, top_counts, i, all_count[i]);
            }
        }
        print_table(top_chars, top_counts);
    }

    private static int push_forw(char[] top_chars, int[] top_counts, int c, int count) {
        for (int i = 9; i > 0; i--) {
            top_chars[i] = top_chars[i - 1];
            top_counts[i] = top_counts[i - 1];
        }
        top_chars[0] = (char) c;
        top_counts[0] = count;
        sort(top_chars, top_counts);
        return top_counts[9];
    }

    private static void sort(char[] top_chars, int[] top_counts) {
        for (int i = 0; i < 10; i++) {
            if (top_counts[i] < top_counts[i + 1]) {
                int count = 0;
                char c = ' ';
                count = top_counts[i];
                top_counts[i] = top_counts[i + 1];
                top_counts[i + 1] = count;
                c = top_chars[i];
                top_chars[i] = top_chars[i + 1];
                top_chars[i + 1] = c;
            } else break;
        }
    }

    private static void print_table(char[] top_chars, int[] top_counts) {
        System.out.println();
        for (int i = 11; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (top_counts[j] * 10 / top_counts[0] == i - 1) {
                    if (top_counts[j] != 0) {
                        System.out.printf("%3d", top_counts[j]);
                    }
                }
                if (top_counts[j] * 10 / top_counts[0] >= i) {
                    System.out.print("  # ");
                } else System.out.print(" ");
            }
            System.out.println();
        }
        for (int i = 0; i < 10; i++)
            if (top_counts[i] != 0) {
                System.out.print("  " + top_chars[i] + " ");
            }
    }

}