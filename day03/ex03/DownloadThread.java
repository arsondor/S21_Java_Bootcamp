import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.Deque;

public class DownloadThread {
    static int countFile = 0;
    static File dir;

    public static void downloadsByThreads(int countThreads, Deque<String> urls) {
        dir = new File(System.getProperty("user.dir") + File.separator + "Downloads");
        if (!dir.exists() && !dir.mkdir()) {
            System.out.println("Error with create dir");
            System.exit(-1);
        }
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            threads[i] = new Thread(task(urls));
            threads[i].start();
        }
    }

    private static synchronized String remove(Deque<String> urls) {
        countFile++;
        if (urls.isEmpty()) {
            return null;
        }
        return countFile + " " + urls.remove();
    }

    private static void downloadFile(String str) throws IOException {
        String[] strs = str.split(" ");
        System.out.println(Thread.currentThread().getName() + " start download file number " + strs[0]);
        URL url = new URL(strs[1]);
        File file = new File(dir + File.separator + getUrlName(url));
        if (file.exists()) {
            System.out.println(Thread.currentThread().getName() + " " + getUrlName(url) + " is already downloaded");
        } else {
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            Files.copy(inputStream, file.toPath());
            System.out.println(Thread.currentThread().getName() + " finish download file number " + strs[0]);
        }
    }

    private static Runnable task(Deque<String> urls) {
        return () -> {
            while (true) {
                String url = remove(urls);
                if (url == null) {
                    return;
                }
                try {
                    downloadFile(url);
                } catch (IOException e) {
                    System.out.println(Thread.currentThread().getName() + " " + e.getMessage());
                }
            }
        };
    }

    private static String getUrlName(URL url) {
        return Paths.get(url.getPath()).getFileName().toString();
    }
}

