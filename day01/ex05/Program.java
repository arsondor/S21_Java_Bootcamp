public class Program {
    public static void main(String[] args) {
        if (args.length == 1) {
            Menu menu = new Menu(args[0]);
            menu.run();
        }
    }
}