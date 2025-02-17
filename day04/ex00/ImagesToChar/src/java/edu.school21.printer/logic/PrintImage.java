package edu.school21.printer.app.logic;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PrintImage {
    char black;
    char white;
    BufferedImage image;

    public PrintImage(String[] args) throws IOException, IllegalArgumentException {
        checkArgs(args);
        black = parseChar(args[0]);
        white = parseChar(args[1]);
        parsePath(args[2]);
    }

    public void print() {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (image.getRGB(x, y) == Color.WHITE.getRGB()) {
                    System.out.print(white);
                } else {
                    System.out.print(black);
                }
            }
            System.out.println();
        }
    }

    private char parseChar(String str) {
        char c;
        String[] strs = str.split("=");
        c = strs[1].charAt(0);
        return c;
    }

    private void parsePath(String str) throws IOException {
        String[] strs = str.split("=");
        File file = new File(strs[1]);
        if (!file.isAbsolute() || !file.exists()) {
            throw new IllegalArgumentException("Path not absolute or file not exists");
        }
        image = ImageIO.read(file);

    }

    private void checkArgs(String[] args) throws IllegalArgumentException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Run with 3 args");
        }
        if (!(args[0].startsWith("--black=") && args[0].split("=").length == 2)) {
            throw new IllegalArgumentException("Illegal arg --black=");
        }
        if (!(args[1].startsWith("--white=") && args[1].split("=").length == 2)) {
            throw new IllegalArgumentException("Illegal arg --white=");
        }
        if (!(args[2].startsWith("--path=") && args[2].split("=").length == 2)) {
            throw new IllegalArgumentException("Illegal arg --path=");
        }
    }
}
