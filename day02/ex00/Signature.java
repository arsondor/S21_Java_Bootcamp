import java.io.*;
import java.util.*;

public class Signature {
    public static HashMap<String, byte[]> read() {
        HashMap<String, byte[]> signature = new HashMap<>();
        try (FileInputStream fin = new FileInputStream("signatures.txt")) {
            Scanner scanner = new Scanner(fin);
            while (scanner.hasNextLine()) {
                String key = scanner.next().replace(',', ' ');
                String[] str = scanner.nextLine().trim().split(" ");
                signature.put(key, hexToByte(str));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return signature;
    }

    private static byte[] hexToByte(String[] str) {
        byte[] value = new byte[str.length];
        for (int i = 0; i < str.length; i++) {
            value[i] = (byte) (Integer.parseInt(str[i], 16) & 0xff);
        }
        return value;
    }
}
