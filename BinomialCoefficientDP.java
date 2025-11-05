import java.util.*;

public class BinomialCoefficientDP {
    
    // Function to calculate binomial coefficient using DP
    static int binomialCoeff(int n, int r) {
        int[][] C = new int[n + 1][r + 1];

        // Build Pascal’s triangle bottom-up
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, r); j++) {
                // Base Cases
                if (j == 0 || j == i)
                    C[i][j] = 1;
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        return C[n][r];
    }

    // Function to print Pascal’s triangle (optional)
    static void printPascal(int n) {
        int[][] C = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    C[i][j] = 1;
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value of n: ");
        int n = sc.nextInt();
        System.out.print("Enter value of r: ");
        int r = sc.nextInt();

        System.out.println("\nBinomial Coefficient C(" + n + ", " + r + ") = " + binomialCoeff(n, r));

        System.out.println("\nPascal’s Triangle up to n = " + n + ":");
        printPascal(n);

        sc.close();
    }
}
