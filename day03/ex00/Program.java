public class Program {
    public static void main(String[] args) {
        int count = checkArgs(args);
        Thread egg = new Egg(count);
        Thread hen = new Hen(count);
        hen.start();
        egg.start();
        try {
            hen.join();
            egg.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }

    private static int checkArgs(String[] args) {
        int count = 0;
        if (!(args.length == 1 && args[0].startsWith("--count="))) {
            System.out.println("Write count like: --count=50");
            System.exit(-1);
        }
        try {
            count = Integer.parseInt(args[0].split("=")[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument. Write count like: --count=50");
            System.exit(-1);
        }
        return count;
    }
}
