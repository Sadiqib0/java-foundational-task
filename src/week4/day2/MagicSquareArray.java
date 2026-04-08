package week4.day2;

import java.util.Arrays;

public class MagicSquareArray {

    public static boolean checkMagicSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length != matrix.length) return false;

        int n = matrix.length;
        int result = Arrays.stream(matrix[0]).sum();

        for (int i = 0; i < n; i++) {
            int sumRow = 0, sumCol = 0;
            for (int j = 0; j < n; j++) {
                sumRow += matrix[i][j];
                sumCol += matrix[j][i];
            }
            if (sumRow != result || sumCol != result) return false;
        }

        int diag1 = 0, diag2 = 0;
        for (int i = 0; i < n; i++) {
            diag1 += matrix[i][i];
            diag2 += matrix[i][n - 1 - i];
        }
        if (diag1 != result || diag2 != result) return false;

        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}};
        System.out.println(checkMagicSquare(matrix));
    }
}
