import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Checker {
    public static void check(String file) {
        HashMap<String, byte[]> signature = Signature.read();
        try (FileInputStream fin = new FileInputStream(file)) {
            byte[] buf = new byte[16];
            boolean flag = false;
            fin.read(buf, 0, buf.length);
            for (Map.Entry<String, byte[]> i : signature.entrySet()) {
                int size = i.getValue().length;
                if (Arrays.equals(buf, 0, size, i.getValue(), 0, size) && !flag) {
                    writeResult(i.getKey());
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("UNDEFINED");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeResult(String str) {
        try (FileOutputStream fos = new FileOutputStream("result.txt", true)) {
            byte[] buf = str.getBytes();
            fos.write(buf, 0, buf.length);
            fos.write('\n');
            System.out.println("PROCESSED");
        } catch (IOException e) {
            System.out.println("Error write");
        }
    }
}
