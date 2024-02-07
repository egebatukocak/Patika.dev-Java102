import java.util.List;

public class OddEvenNumFinder implements Runnable {
    private final List<Integer> numbers;
    private final List<Integer> evenNumbers;
    private final List<Integer> oddNumbers;

    public OddEvenNumFinder(List<Integer> numbers, List<Integer> evenNumbers, List<Integer> oddNumbers) {
        this.numbers = numbers;
        this.evenNumbers = evenNumbers;
        this.oddNumbers = oddNumbers;
    }

    @Override
    public void run() {
        for (Integer i : numbers) {
            if (i % 2 == 0) {
                synchronized (evenNumbers) {
                    evenNumbers.add(i);
                }
            } else {
                synchronized (oddNumbers) {
                    oddNumbers.add(i);
                }
            }
        }
    }
}
