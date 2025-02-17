package ex00.Game.src.main.java.game;

import com.diogonunes.jcdp.color.ColoredPrinter;
import java.util.*;

public class GameMap {
    private final Sprite[] sprites;
    private final int[][] map;
    private final ColoredPrinter printer;


    public GameMap(Sprite[] sprites, Args jArgs) {
        this.sprites = sprites;
        this.printer = new ColoredPrinter();
        map = new int[jArgs.getSize()][jArgs.getSize()];
        generateMap(jArgs);
    }

    private void generateMap(Args jArgs) {
        int size = jArgs.getSize();
        int enemies = jArgs.getEnemies();
        int obstacles = jArgs.getObstacles();
        Random rnd = new Random();
        int xPlayer, yPlayer, xExit, yExit, x, y;
        do {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    map[i][j] = 0;
                }
            }
            int tempObstacles = obstacles;
            xPlayer = rnd.nextInt(size);
            yPlayer = rnd.nextInt(size);
            map[xPlayer][yPlayer] = 1;
            do {
                xExit = rnd.nextInt(size);
                yExit = rnd.nextInt(size);
            } while (map[xExit][yExit] != 0);
            map[xExit][yExit] = 2;
            while (tempObstacles != 0) {
                x = rnd.nextInt(size);
                y = rnd.nextInt(size);
                if (map[x][y] != 0) {
                    continue;
                }
                map[x][y] = 3;
                tempObstacles--;
            }
        } while (!pathExists(map, xPlayer, yPlayer, xExit, yExit));
        while (enemies != 0) {
            x = rnd.nextInt(size);
            y = rnd.nextInt(size);
            if (map[x][y] != 0) {
                continue;
            }
            map[x][y] = 4;
            enemies--;
        }
        ;
    }


    private boolean pathExists(int[][] map, int xPlayer, int yPlayer, int xExit, int yExit) {
        int n = map.length;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{xPlayer, yPlayer});

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == xExit && y == yExit) {
                return true;
            }

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < n && newY >= 0 && newY < n && map[newX][newY] != 3 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
        return false;
    }

    public boolean changeMap(int oldX, int oldY, int newX, int newY) {
        boolean flag = false;
        if (newX >= 0 && newY >= 0 && newX < map.length && newY < map.length && map[newX][newY] == 0) {
            int temp = map[oldX][oldY];
            map[oldX][oldY] = map[newX][newY];
            map[newX][newY] = temp;
            flag = true;
        }
        return flag;
    }

    public void printMap(boolean profile) {
        if(!profile){
        System.out.print("\033[H\033[2J");
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                sprites[map[j][i]].printSprite(printer);
            }
            System.out.println();
        }
    }

    public int[][] getMap() {
        return map;
    }
}