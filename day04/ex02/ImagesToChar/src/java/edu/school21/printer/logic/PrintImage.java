package edu.school21.printer.logic;

import com.beust.jcommander.JCommander;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrintImage {
    String black;
    String white;
    BufferedImage image;

    public PrintImage(String[] args, String filepath) throws IOException, IllegalArgumentException {
        ColorArgs jArgs = new ColorArgs();
        JCommander cmd = JCommander.newBuilder().addObject(jArgs).build();
        cmd.parse(args);
        black = jArgs.getBlack();
        white = jArgs.getWhite();
        parsePath(filepath);
    }

    public void print() {
        ColoredPrinter printer = new ColoredPrinter();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (image.getRGB(x, y) == Color.WHITE.getRGB()) {
                    printer.print(" ", Ansi.Attribute.NONE,
                            Ansi.FColor.NONE, Ansi.BColor.valueOf(white));
                } else {
                    printer.print(" ", Ansi.Attribute.NONE,
                            Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                }
            }
            System.out.println();
        }
    }

    private void parsePath(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not exists");
        }
        image = ImageIO.read(file);
    }

}
