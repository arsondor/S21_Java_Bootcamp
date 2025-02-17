public class Tasks {
    private boolean flag = true;

    public synchronized void taskEgg() throws InterruptedException {
        while (!flag) {
            wait();
        }
        flag = false;
        System.out.println("Egg");
        notify();
    }

    public synchronized void taskHen() throws InterruptedException {
        while (flag) {
            wait();
        }
        flag = true;
        System.out.println("Hen");
        notify();
    }
}
