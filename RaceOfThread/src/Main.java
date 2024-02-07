import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            numbers.add(i);
        }

        List<Integer> oddNumbers = new ArrayList<>();
        List<Integer> evenNumbers = new ArrayList<>();

        int threadCount = 4;
        int sublistSize = numbers.size() / threadCount;
        List<Runnable> finders = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            List<Integer> sublist = numbers.subList(sublistSize * i, (i + 1) * sublistSize);
            Runnable finder = new OddEvenNumFinder(sublist, evenNumbers, oddNumbers);
            finders.add(finder);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (Runnable finder : finders) {
            executorService.execute(finder);
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {

        }
        System.out.println("\n\nEven Numbers: " + evenNumbers);
        System.out.println("\n\nOdd Numbers: " + oddNumbers);
    }
}