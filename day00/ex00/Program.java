public class Program {
    public static void main(String[] args) {
    int res = 0;
    int digit = 666666;
    res += digit % 10;
    digit = digit/10;
        res += digit % 10;
        digit = digit/10;
        res += digit % 10;
        digit = digit/10;
        res += digit % 10;
        digit = digit/10;
        res += digit % 10;
        digit = digit/10;
        res += digit % 10;
    System.out.println(res);
    }
}