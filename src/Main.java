import java.util.*;
public class Main {

    public static int[][] ways = {
            {-1, 0},
            {0, -1},
            {1, 0},
            {0, 1},
    };

    public static void main(String[] args) {
        int [][] maze = new int[][] {
                {1, 0, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 1},
        };
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println(
                "bfs:: maze walkthrough: " + maze_bfs(maze) + "!"
        );
        System.out.println("\n\n\n\n\n\n\n\n");
    }

    public static int maze_bfs(int[][] maze) {
        Queue<Integer[]> travel = new LinkedList<Integer[]>();

        Integer[] pos = {0, 0, 0}; // pos[2] = answer
        travel.add(pos);

        while(!travel.isEmpty()  ) {
            pos = travel.poll();
            for(int i = 0; i < ways.length; i ++) {
                int nextX = pos[0] + ways[i][0];
                int nextY = pos[1] + ways[i][1];
                if(validationCheck(maze, nextX, nextY)) {
                    travel.add(new Integer[] {nextX, nextY, pos[2]+1});
                    maze[pos[0]][pos[1]] = 0;
                    if(nextX == maze.length - 1 && nextY == maze[0].length - 1) {
                        return pos[2] + 1;
                    }
                }
            }

        }
        return -1;
    }

    public static boolean validationCheck(int[][] maze, int nextX, int nextY) {
        return nextX >= 0 && nextX < maze.length
                && nextY >= 0 && nextY < maze[0].length
                && maze[nextX][nextY] == 1;
    }

    public static void maze_status(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " - ");
            }
            System.out.println();
        }
    }
}
