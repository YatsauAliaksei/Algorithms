package com.ttrlalgs.challenge;

public class DFS {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 1},
                {1, 0, 0, 1, 0, 1},
                {0, 1, 0, 1, 0, 1},
                {1, 1, 0, 1, 0, 1},
        };

        // up / right
        int[][] positions = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};

        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int s = 0; s < grid[i].length; s++) {
                max = Math.max(max, checkNeightberhoods(grid, i, s, positions));
            }
        }
        System.out.println(max);
    }

    private static int checkNeightberhoods(int[][] grid, int n, int m, int[][] position) {
        if(n < 0 || m < 0 || grid.length <= n || grid[0].length <= m) {
            return 0;
        }

        int val = grid[n][m];
        if (val == 1) {
            int counter = 1;
            grid[n][m] = 0;
            for (int[] pos : position) {
                counter += checkNeightberhoods(grid, n + pos[0], m + pos[1], position);
            }
            return counter;
        } else
            return 0;

    }
}
