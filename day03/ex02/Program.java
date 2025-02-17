public class Program {

    public static void main(String[] args) {
        Input.checkArgs(args);
        int[] arr = Input.inputArr();
        System.out.println("Sum: " + SumArr.sumArr(0, arr.length, arr));
        try {
            SumArr.sumWithThreads(arr, Input.getCountThreads());
        } catch (InterruptedException e) {
            System.out.println("Error");
        }

    }
}