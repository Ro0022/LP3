public class EightQueens {

    static final int N = 8; // Size of the chessboard

    // Function to print the solution matrix
    static void printSolution(int[][] board) {
        System.out.println("Final 8-Queens Arrangement:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    // Check if a queen can be placed safely on board[row][col]
    static boolean isSafe(int[][] board, int row, int col) {
        int i, j;

        // Check this column on upper side
        for (i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        // Check upper left diagonal
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check upper right diagonal
        for (i = row, j = col; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Recursive function to solve the N-Queens problem
    static boolean solveNQUtil(int[][] board, int row) {
        // Base case: If all queens are placed
        if (row >= N)
            return true;

        // Skip row 0 because first Queen is already placed
        if (row == 0)
            return solveNQUtil(board, row + 1);

        // Try placing this queen in all columns one by one
        for (int col = 0; col < N; col++) {
            // Check if queen can be placed
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // Place queen

                // Recur to place rest of the queens
                if (solveNQUtil(board, row + 1))
                    return true;

                // If placing queen doesn't lead to solution, backtrack
                board[row][col] = 0;
            }
        }

        // If queen can't be placed in any column, return false
        return false;
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];

        // Place first queen manually (for example, at position [0][0])
        board[0][0] = 1;

        if (!solveNQUtil(board, 0))
            System.out.println("Solution does not exist");
        else
            printSolution(board);
    }
}
