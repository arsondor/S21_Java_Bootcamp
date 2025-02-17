package ex00.ChaseLogic.src.main.java.chaselogic;

import java.util.*;

public class ChaseLogic {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        int x, y, g, h;

        Node(int x, int y, int g, int h) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
        }

        public int compareTo(Node n) {
            return Integer.compare(g + h, n.g + n.h);
        }
    }

    public static int[] getNextMove(int[][] grid, int startX, int startY, int targetX, int targetY) {
        grid[startX][startY] = 0;
        grid[targetX][targetY] = 0;
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<String, String> cameFrom = new HashMap<>();
        Set<String> visited = new HashSet<>();

        openSet.add(new Node(startX, startY, 0, Math.abs(startX - targetX) + Math.abs(startY - targetY)));
        cameFrom.put(startX + "," + startY, null);

        while (!openSet.isEmpty()) {
            Node cur = openSet.poll();
            if (cur.x == targetX && cur.y == targetY) break;
            visited.add(cur.x + "," + cur.y);
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i], ny = cur.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length &&
                        grid[nx][ny] == 0 && visited.add(nx + "," + ny)) { // Только пустые клетки
                    openSet.add(new Node(nx, ny, cur.g + 1, Math.abs(nx - targetX) + Math.abs(ny - targetY)));
                    cameFrom.putIfAbsent(nx + "," + ny, cur.x + "," + cur.y);
                }
            }
        }
        grid[startX][startY] = 4;
        grid[targetX][targetY] = 1;
        return reconstructPath(cameFrom, startX, startY, targetX, targetY);
    }

    private static int[] reconstructPath(Map<String, String> cameFrom, int startX, int startY, int targetX,
                                         int targetY) {
        List<int[]> path = new ArrayList<>();
        for (String cur = targetX + "," + targetY; cur != null; cur = cameFrom.get(cur)) {
            String[] parts = cur.split(",");
            path.add(new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])});
        }
        Collections.reverse(path);
        return path.size() > 1 ? path.get(1) : new int[]{startX, startY}; // Возвращаем следующий шаг или начальную позицию
    }
}