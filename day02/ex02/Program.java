import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Program {
    static Path path;

    public static void main(String[] argv) {
        if (argv.length == 1 && argv[0].startsWith("--current-folder=")) {
            String[] str = argv[0].split("=");
            path = Paths.get(str[1]);
            if (path.isAbsolute() && Files.exists(path)) {
                Scanner scan = new Scanner(System.in);
                while (true) {
                    str = scan.nextLine().trim().split(" ");
                    if (str.length == 1 && str[0].equals("ls")) {
                        execLs();
                    } else if (str.length == 2 && str[0].equals("cd")) {
                        execCd(str[1]);
                    } else if (str.length == 3 && str[0].equals("mv")) {
                        execMv(str[1], str[2]);
                    } else if (str.length == 1 && str[0].equals("exit")) {
                        break;
                    } else {
                        System.out.println("Unknown command");
                    }
                }
            } else System.out.println("Path on parameter may be absolute or path not exists");
        } else {
            System.out.println("Wrong parameter");
        }
    }

    private static void execCd(String rPath) {
        Path temp = Path.of(rPath);
        if (!temp.isAbsolute()) {
            temp = path.resolve(temp).normalize();
        }
        if (Files.exists(temp) && Files.isDirectory(temp)) {
            path = temp;
            System.out.println(path);
        } else {
            System.out.println("cd: no such file or directory: " + rPath);
        }
    }

    private static long getDirectorySize(File directory) {
        long size = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File i : files) {
                if (i.isFile()) {
                    size += i.length();
                } else {
                    size += getDirectorySize(i);
                }
            }
        }
        return size;
    }

    private static void execLs() {
        File[] files = path.toFile().listFiles();
        float size = 0;
        if (files != null) {
            for (File i : files) {
                if (i.isDirectory()) {
                    size = getDirectorySize(i);
                } else {
                    size = i.length();
                }
                System.out.println(i.getName() + " " + Math.round(size / 1000) + " KB");
            }
        }
    }

    private static void execMv(String source, String target) {
        Path sourcePath = path.resolve(Paths.get(source)).normalize();
        Path targetPath = path.resolve(Paths.get(target)).normalize();
        if (Files.isDirectory(targetPath)) {
            targetPath = targetPath.resolve(sourcePath.getFileName());
        }
        try {
            Files.move(sourcePath, targetPath);
        } catch (IOException e) {
            System.out.println("mv: invalid path" + e.getMessage());
        }
    }
}
