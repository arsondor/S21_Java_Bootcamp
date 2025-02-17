import java.io.*;
import java.util.*;

public class ReadFiles {
    public static void readFiles(String[] files) throws IOException {
        Set<String> dictionary = new HashSet<>();
        parseWords(files[0], dictionary);
        parseWords(files[1], dictionary);
        writeDictionary(dictionary);
        int[][] vector = new int[2][dictionary.size()];
        readWords(files[0], dictionary, vector[0]);
        readWords(files[1], dictionary, vector[1]);
        getSimilarity(vector);
    }

    private static void parseWords(String file, Set<String> dictionary) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = "";
        while ((str = br.readLine()) != null) {
            dictionary.addAll(Arrays.asList(str.split(" ")));
        }
        br.close();
    }

    private static void writeDictionary(Set<String> dictionary) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dictionary.txt"))) {
            for (String s : dictionary) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readWords(String file, Set<String> dictionary, int[] vector) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        while ((str = br.readLine()) != null) {
            for (String i : str.split(" ")) {
                if (dictionary.contains(i)) {
                    vector[getIndex(i, dictionary)]++;
                }
            }
        }
    }

    private static int getIndex(String str, Set<String> dictionary) {
        int index = 0;
        for (String i : dictionary) {
            if (str.equals(i)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static void getSimilarity(int[][] vector) {
        double numerator = 0, denominatorA = 0, denominatorB = 0, similarity = 0;
        for (int i = 0; i < vector[0].length; i++) {
            numerator += vector[0][i] * vector[1][i];
            denominatorA += vector[0][i] * vector[0][i];
            denominatorB += vector[1][i] * vector[1][i];
        }
        similarity = numerator / (Math.sqrt(denominatorA) * Math.sqrt(denominatorB));
        similarity = Math.floor(similarity * 100) / 100;
        System.out.println("Similarity = " + similarity);
    }
}

