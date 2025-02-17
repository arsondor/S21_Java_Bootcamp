import java.util.Deque;

public class Program {
    public static void main(String[] args) {
        int countThreads = Input.checkArgs(args);
        Deque<String> urls = Input.readFile("files_urls.txt");
        DownloadThread.downloadsByThreads(countThreads, urls);

    }
}
