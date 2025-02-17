package ex00.Game.src.main.java.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private final String configPath;

    public ConfigLoader(String profile) {
        this.configPath = "Game/src/main/resources/application-" + profile + ".properties";
    }

    public Sprite[] loadSprites() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(configPath)) {
            properties.load(fis);
        }

        return new Sprite[]{
                new Sprite(properties.getProperty("empty.char").charAt(0), properties.getProperty("empty.color")),
                new Sprite(properties.getProperty("hero.char").charAt(0), properties.getProperty("hero.color")),
                new Sprite(properties.getProperty("exit.char").charAt(0), properties.getProperty("exit.color")),
                new Sprite(properties.getProperty("wall.char").charAt(0), properties.getProperty("wall.color")),
                new Sprite(properties.getProperty("monster.char").charAt(0), properties.getProperty("monster.color"))};
    }
}
