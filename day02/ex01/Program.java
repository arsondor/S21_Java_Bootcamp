import java.io.IOException;

public class Program {
    public static void main(String[] argv) {
        try {
            if (argv.length != 2) {
                throw new IllegalArgumentException("Write only 2 files in parameters");
            }
            ReadFiles.readFiles(argv);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


}
