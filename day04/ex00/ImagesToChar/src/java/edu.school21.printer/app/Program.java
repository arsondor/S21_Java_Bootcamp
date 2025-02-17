package edu.school21.printer.app;

import edu.school21.printer.app.logic.PrintImage;

import java.io.IOException;


public class Program {
    public static void main(String[] args) {
        try {
            PrintImage printImage = new PrintImage(args);
            printImage.print();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
