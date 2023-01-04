package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts  = new AList<>();

        int baseValue = 1000;
        int opNum = 10000;
        for (int i = 0; i < 8; i++) {
            // Ns
            Ns.addLast(baseValue * (int) Math.pow(2, i));

            // times
            // 1. Create an SLList.
            SLList<Integer> List = new SLList<>();
            // 2. Add N items to the SLList.
            for (int j = 0; j < Ns.get(i); j++) {
                List.addLast(j);
            }
            // 3. Start the timer.
            Stopwatch sw = new Stopwatch();
            // 4. Perform M getLast operations on the SLList.
            for (int k = 1; k < opNum; k++) {
                List.getLast();
            }
            // 5. Check the timer. This gives the total time to complete all M operations.
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);

            // opCounts
            opCounts.addLast(opNum);
        }

        printTimingTable(Ns, times, opCounts);
    }

}
