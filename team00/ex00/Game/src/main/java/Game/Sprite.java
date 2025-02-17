package ex00.Game.src.main.java.game;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

public class Sprite {
    private final char object;
    private final String color;


    public Sprite(char object, String color) {
        this.object = object;
        this.color = color;

    }

    public void printSprite(ColoredPrinter p) {
        p.print(object, Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(color));
    }

    public char getObject() {
        return object;
    }
}
