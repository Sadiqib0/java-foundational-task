
public class ArrayOfArray {
    public static void main(String[] args) {
        char[][] arrayOfArrays = {
                {'X', 'O', 'X'},
                {'X', 'X', 'O'},
                {'O', 'X', 'X'}
        };

        for (char[] row : arrayOfArrays) {
            for (char column : row) {
                int value = (column == 'X') ? 1 : 0;
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}