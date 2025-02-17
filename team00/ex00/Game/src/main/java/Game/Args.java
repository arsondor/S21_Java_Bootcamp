package ex00.Game.src.main.java.game;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = "--enemiesCount", required = true)
    private int enemies;
    @Parameter(names = "--wallsCount", required = true)
    private int obstacles;

    @Parameter(names = "--size", required = true)
    private int size;
    @Parameter(names = "--profile", required = true)
    private String profile;


    public int getEnemies() {
        return enemies;
    }

    public int getObstacles() {
        return obstacles;
    }

    public int getSize() {
        return size;
    }

    public String getProfile() {
        return profile;
    }
}

