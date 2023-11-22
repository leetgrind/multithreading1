import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariable {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        AtomicCounter atomicCounter = new AtomicCounter();

        for(int i=0; i<10; i++) {
            executorService.submit(() -> atomicCounter.increase());
        }

        executorService.shutdown();
        System.out.println("Final counter: " + atomicCounter.getCount());

    }
}

class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increase() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
