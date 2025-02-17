public class SumArr {
    private static int result = 0;

    public static void sumWithThreads(int[] arr, int countThreads) throws InterruptedException {
        Thread[] threads = new Thread[countThreads];
        int range = arr.length / countThreads;
        for (int i = 0; i < countThreads; i++) {
            if (i != countThreads - 1) {
                threads[i] = new Thread(getTask(i * range, i * range + range, arr));
            } else {
                threads[i] = new Thread(getTask(i * range, arr.length, arr));
            }
            threads[i].start();
            threads[i].join();
        }
        System.out.println("Sum by threads: " + result);
    }

    public static synchronized int sumArr(int start, int end, int[] arr) {
        int res = 0;
        for (int i = start; i < end; i++) {
            res += arr[i];
        }
        return res;
    }

    private static Runnable getTask(int start, int end, int[] arr) {
        return () -> {
            int res = sumArr(start, end, arr);
            result += res;
            System.out.println(Thread.currentThread().getName() + ": from " + start + " to " + end + " sum is " + res);
        };
    }
}
