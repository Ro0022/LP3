import java.util.Scanner;

public class FibonacciSeries {

    static int recursiveSteps = 0;
    static int iterativeSteps = 0;

    // Recursive Fibonacci with step count
    static int fibRecursive(int n) {
        recursiveSteps++; // Count each function call
        if (n <= 1)
            return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    // Iterative Fibonacci with step count
    static void fibIterativeSeries(int n) {
        int a = 0, b = 1, c;

        System.out.print("Iterative Fibonacci Series: ");
        if (n >= 1) {
            System.out.print(a + " ");
            iterativeSteps++;
        }
        if (n >= 2) {
            System.out.print(b + " ");
            iterativeSteps++;
        }

        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
            System.out.print(c + " ");
            iterativeSteps += 3;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of terms (n): ");
        int n = sc.nextInt();
        sc.close();

        // Recursive Series
        System.out.print("Recursive Fibonacci Series: ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibRecursive(i) + " ");
        }
        System.out.println();
        System.out.println("Recursive Step Count: " + recursiveSteps);

        // Iterative Series
        fibIterativeSeries(n);
        System.out.println("Iterative Step Count: " + iterativeSteps);
    }
}
