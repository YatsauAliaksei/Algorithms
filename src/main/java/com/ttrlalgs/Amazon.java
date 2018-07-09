package com.ttrlalgs;

public class Amazon {

    public static void main(String[] args) {
        int[][] A = new int[7][3];
        A[0][0] = 5;
        A[0][1] = 4;
        A[0][2] = 4;
        A[1][0] = 4;
        A[1][1] = 3;
        A[1][2] = 4;
        A[2][0] = 3;
        A[2][1] = 2;
        A[2][2] = 4;
        A[3][0] = 2;
        A[3][1] = 2;
        A[3][2] = 2;
        A[4][0] = 3;
        A[4][1] = 3;
        A[4][2] = 4;
        A[5][0] = 1;
        A[5][1] = 4;
        A[5][2] = 4;
        A[6][0] = 4;
        A[6][1] = 1;
        A[6][2] = 1;
        int solution = solution(A);
        System.out.println(solution);
    }

    private final static int DIRECTIONS[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static int solution(int[][] A) {
        boolean[][] visited = new boolean[A.length][A[0].length];

        int result = 0;
        for (int i = 0; i < A.length; i++) {
            for (int k = 0; k < A[i].length; k++) {

                if (visited[i][k])
                    continue;

                visited[i][k] = true;

                checkSides(A, i, k, A[i][k], visited);
                result++;
            }
        }

        return result;
    }

    private static void checkSides(int[][] a, int i, int k, int color, boolean[][] visited) {
        for (int[] direction : DIRECTIONS) {
            int x = i + direction[0];
            int y = k + direction[1];
            if (x < 0 || y < 0 || x >= a.length || y >= a[0].length)
                continue;

            if (visited[x][y])
                continue;

            int curColor = a[x][y];
            if (curColor == color) {
                visited[x][y] = true;
                checkSides(a, x, y, color, visited);
            }
        }
    }
}
