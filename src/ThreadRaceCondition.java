import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadRaceCondition {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Counter counter =  new Counter();

        for(int i=0; i<10; i++) {
            executorService.submit(counter::increase);
        }

        executorService.shutdown();

        System.out.println(counter.getCount());

    }
}

class Counter {
    private int count = 0;

    public void increase() {
        synchronized (this) {
            ++this.count;
        }
    }

    public int getCount() {
        return this.count;
    }
}
