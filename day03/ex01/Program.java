public class Program {

    public static void main(String[] args) throws InterruptedException {
        int count = checkArgs(args);
        Tasks task = new Tasks();
        final Thread egg = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                try {
                    task.taskEgg();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        final Thread Hen = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                try {
                    task.taskHen();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        egg.start();
        Hen.start();
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
