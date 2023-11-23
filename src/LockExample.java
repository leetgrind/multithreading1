import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        LockCounter lockCounter = new LockCounter();

        executorService.submit(() -> {
            System.out.println("Locking by First thread");
            lockCounter.increase();
        });

        executorService.submit(() -> {
            System.out.println("Locking by Second thread");
            lockCounter.increase();
        });

        executorService.shutdown();

    }
}

class LockCounter {

    private Lock lock = new ReentrantLock();

    private int count = 0;

    public void increase() {
        lock.lock();
        try {
            count = count + 1;
            System.out.println(Thread.currentThread().getName() +
                    " increased the counter to " + count);
        }
        finally {
            System.out.println(Thread.currentThread().getName() + " releasing the lock");
            lock.unlock();
        }

    }

}
