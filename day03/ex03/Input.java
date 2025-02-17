import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;

public class Input {
    public static int checkArgs(String[] args) {
        if (!(args.length == 1 && args[0].startsWith("--threadsCount"))) {
            System.out.println("Write like: --threadsCount=3");
            System.exit(-1);
        }
        int countThreads = 0;
        try {
            countThreads = Integer.parseInt(args[0].split("=")[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal args");
            System.exit(-1);
        }
        if (countThreads < 1) {
            System.out.println("Count threads must be less size of array");
            System.exit(-1);
        }
        return countThreads;
    }

    public static Deque<String> readFile(String file) {
        Deque<String> urls = new ArrayDeque<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = "";
            while ((str = br.readLine()) != null) {
                urls.add(str);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return urls;
    }
}

