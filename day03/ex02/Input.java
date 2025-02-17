public class Input {
    private static int sizeArr = 0;
    private static int countThreads = 0;

    public static void checkArgs(String[] args) {
        if (!(args.length == 2 && args[0].startsWith("--arraySize=") && args[1].startsWith("--threadsCount"))) {
            System.out.println("Write like: --arraySize=13 --threadsCount=3");
            System.exit(-1);
        }
        try {
            sizeArr = Integer.parseInt(args[0].split("=")[1]);
            countThreads = Integer.parseInt(args[1].split("=")[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal args");
            System.exit(-1);
        }
        if (sizeArr > 2000000 || sizeArr < 0) {
            System.out.println("Array must be in the range 0 - 2 000 000");
            System.exit(-1);
        }
        if (countThreads > sizeArr || countThreads < 1) {
            System.out.println("Count threads must be less size of array");
            System.exit(-1);
        }
    }

    public static int[] inputArr() {
        int[] arr = new int[sizeArr];
        for (int i = 0; i < sizeArr; i++) {
            arr[i] = (int) (Math.random() * 2000 - 1000);
        }
        return arr;
    }

    public static int getCountThreads() {
        return countThreads;
    }
}
