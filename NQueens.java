import java.util.Scanner;

public class NQueens {
    
    static int N;
    
    // Function to print the matrix
    static void printBoard(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    // Check if it's safe to place a queen at board[row][col]
    static boolean isSafe(int board[][], int row, int col) {
        int i, j;

        // Check this column on upper side
        for (i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        // Check upper-left diagonal
        for (i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check upper-right diagonal
        for (i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Solve N Queens using backtracking
    static boolean solveNQUtil(int board[][], int row) {
        // Base case: if all queens placed
        if (row == N)
            return true;

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;  // place queen

                // Recur to place the rest
                if (solveNQUtil(board, row + 1))
                    return true;

                // Backtrack if placing queen here doesn’t lead to a solution
                board[row][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value of N: ");
        N = sc.nextInt();

        int[][] board = new int[N][N];

        System.out.println("Enter the position (row and column) of the first queen (0-indexed): ");
        int row = sc.nextInt();
        int col = sc.nextInt();

        // Place first queen
        board[row][col] = 1;

        // Start backtracking from next row
        if (solveNQUtil(board, row + 1))
            printBoard(board);
        else
            System.out.println("No solution exists with given first queen position.");
    }
}
