import java.util.*;

class Job {
    char id;       // Job ID (A, B, C, etc.)
    int deadline;  // Deadline of job
    int profit;    // Profit if completed before or on deadline

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();
        Job[] jobs = new Job[n];

        // Input job details
        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter Job ID (single letter): ");
            char id = sc.next().charAt(0);
            System.out.print("Enter Deadline: ");
            int deadline = sc.nextInt();
            System.out.print("Enter Profit: ");
            int profit = sc.nextInt();
            jobs[i] = new Job(id, deadline, profit);
        }

        // Sort jobs by decreasing profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs)
            if (job.deadline > maxDeadline)
                maxDeadline = job.deadline;

        // Create time slots
        char[] result = new char[maxDeadline];
        boolean[] slot = new boolean[maxDeadline];

        // Initialize slots
        Arrays.fill(slot, false);

        // Schedule jobs
        for (Job job : jobs) {
            // Find free slot from job.deadline - 1 to 0
            for (int j = job.deadline - 1; j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = job.id;
                    slot[j] = true;
                    break;
                }
            }
        }

        // Print result
        System.out.println("\nScheduled Jobs:");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i])
                System.out.print(result[i] + " ");
        }

        // Calculate total profit
        int totalProfit = 0;
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                for (Job job : jobs) {
                    if (job.id == result[i])
                        totalProfit += job.profit;
                }
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);

        sc.close();
    }
}
