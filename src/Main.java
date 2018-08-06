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
        System.out.println(
                "bfs:: maze walkthrough: " + maze_bfs(maze) + "!"
        );
    }

    /*
     * main function to solve problem.
     * BFS: search maze with queue which stores the array.
     * the array stores next position x, y, and count walk through.
     * all walk-through is stores in order of count,
     * so first finding in bfs is also fastest path of maze.
     */
    public static int maze_bfs(int[][] maze) {
        Queue<Integer[]> travel = new LinkedList<Integer[]>();

        Integer[] pos = {0, 0, 0};  // pos: x, y, answer
        travel.add(pos);            // we start with position 0, 0 and count 0.
                                    // the queue struct like [ {0, 0, 0}, ... ]

        /*
         * our logic for bfs is...
         * 1. get fastest position in queue(pop!)
         * 2. now our current position is in 'pos'
         * 3. check validate ways(or, path) that current position can reach on next 1 step
         * 4. if the walk is safe, we put in to queue.
         * 5. we will not come back to current position, so change current position value into 0.
         *      -- current state is most fastest reaching of current position!
         *      so we don't need to concern about the other path to reach on 'the position' --
         * 6. repeat. if we found the way, we returns it. if the queue is empty, it means there is no path to reach goal. return -1.
         */
        while(!travel.isEmpty()  ) {
            pos = travel.poll();
            for(int i = 0; i < ways.length; i ++) {
                int nextX = pos[0] + ways[i][0];
                int nextY = pos[1] + ways[i][1];
                if(validationCheck(maze, nextX, nextY)) {
                    travel.add(new Integer[] {nextX, nextY, pos[2]+1}); // next step is safe. let's store in queue.
                    maze[pos[0]][pos[1]] = 0;                           // destroy the way to back.
                    if(nextX == maze.length - 1 && nextY == maze[0].length - 1) {
                        // we reach the goal!
                        return pos[2] + 1;
                    }
                }
            }

        }
        return -1;
    }

    /*
     * validationCheck: maze, x, y > boolean
     * if input position is good to go, it returns ture.
     * else, returns false.
     */
    public static boolean validationCheck(int[][] maze, int nextX, int nextY) {
        return nextX >= 0 && nextX < maze.length        // check index bound of x.
                && nextY >= 0 && nextY < maze[0].length // check index bound of y
                && maze[nextX][nextY] == 1;             // check there is path or not.
    }

    /*
     * helper function 1. printing maze status
     *
     */
    public static void maze_status(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " - ");
            }
            System.out.println();
        }
    }
}
