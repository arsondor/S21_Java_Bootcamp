package ex00.Game.src.main.java.game;

import com.beust.jcommander.JCommander;
import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        Args jArgs = new Args();
        JCommander cmd = JCommander.newBuilder().addObject(jArgs).build();
        cmd.parse(args);

        String profile = jArgs.getProfile();
        if (profile == null || (!profile.equals("dev") && !profile.equals("production"))) {
            System.out.println("Invalid or missing profile argument. Use --profile=dev or --profile=production");
            return;
        }

        try {
            ConfigLoader configLoader = new ConfigLoader(profile);
            Sprite[] sprites = configLoader.loadSprites();

            if (jArgs.getSize() * jArgs.getSize() - jArgs.getObstacles() - jArgs.getEnemies() - 2 < 0) {
                throw new IllegalParametersException("Bad args");
            }

            Game game = new Game(sprites, jArgs);
            game.executeGame();
        } catch (IOException e) {
            System.out.println("Error loading config file: " + e.getMessage());
        } catch (IllegalParametersException e) {
            System.out.println(e.getMessage());
        }
    }
}
