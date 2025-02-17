package ex00.Game.src.main.java.game;

import java.util.Scanner;
import ex00.ChaseLogic.src.main.java.chaselogic.ChaseLogic;

public class Game {
    private final GameMap map;
    private int[] posPlayer;
    private int[] posExit;
    private final int[][] posEnemies;
    private final Scanner scanner;
    private final boolean profile;

    public Game(Sprite[] sprites, Args jArgs) {
        profile = jArgs.getProfile().equals("dev");
        map = new GameMap(sprites, jArgs);
        posEnemies = new int[jArgs.getEnemies()][2];
        getPosObjects();
        scanner = new Scanner(System.in);
    }

    private void getPosObjects() {
        int[][] arr = map.getMap();
        int countEnemies = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][i] == 1) {
                    posPlayer = new int[]{j, i};
                } else if (arr[j][i] == 2) {
                    posExit = new int[]{j, i};
                } else if (arr[j][i] == 4) {
                    posEnemies[countEnemies][0] = j;
                    posEnemies[countEnemies][1] = i;
                    countEnemies++;
                }
            }
        }
    }

    public void executeGame() {
        boolean flag = false;
        while (true) {
            if (movePlayer()) {
                break;
            }
            if (moveEnemies()) {
                break;
            }
        }
    }

    private boolean movePlayer() {
        int x, y;
        do {
            map.printMap(profile);
            x = 0;
            y = 0;
            char c = scanner.next().charAt(0);
            switch (c) {
                case ('w'):
                    y = -1;
                    break;
                case ('a'):
                    x = -1;
                    break;
                case ('s'):
                    y = 1;
                    break;
                case ('d'):
                    x = 1;
                    break;
                case ('9'):
                    System.out.println("YOU LOST");
                    return true;
            }
            x += posPlayer[0];
            y += posPlayer[1];
            if (posExit[0] == x && posExit[1] == y) {
                System.out.println("YOU WIN");
                return true;
            }
        } while (!map.changeMap(posPlayer[0], posPlayer[1], x, y));
        posPlayer[0] = x;
        posPlayer[1] = y;
        return false;
    }

    private boolean moveEnemies() {
        for (int i = 0; i < posEnemies.length; i++) {
            if (profile) {
                map.printMap(true);
            }
            int[] move = ChaseLogic.getNextMove(map.getMap(), posEnemies[i][0], posEnemies[i][1], posPlayer[0],
                    posPlayer[1]);
            map.changeMap(posEnemies[i][0], posEnemies[i][1], move[0], move[1]);
            if (move[0] == posPlayer[0] && move[1] == posPlayer[1]) {
                map.printMap(profile);
                System.out.println("YOU LOST");
                return true;
            }
            posEnemies[i] = move;
            while (profile) {
                if (scanner.next().charAt(0) == '8') {
                    break;
                }
            }
        }
        return false;
    }

}
