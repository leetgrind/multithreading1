import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        LockCounter lockCounter = new LockCounter();

        executorService.submit(() -> {
            System.out.println("Locking by First thread");
            try {
                lockCounter.increaseWithStatements();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            System.out.println("Locking by Second thread");
            try {
                lockCounter.increaseWithStatements();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();

        System.out.println("Final value of the counter " + lockCounter.getCount());

    }
}

class LockCounter {

    private ReentrantLock lock = new ReentrantLock();

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

    public int getCount() {
        return this.count;
    }

    public void increaseWithStatements() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " Is it locked: " + lock.isLocked());

        System.out.println(Thread.currentThread().getName()+ " If is lock is acquired by current thread " + lock.isHeldByCurrentThread());

        boolean isAcquired = lock.tryLock(4, TimeUnit.SECONDS);

        System.out.println(Thread.currentThread().getName() + " Lock acquired " + isAcquired);

        if(isAcquired) {
            try{
                System.out.println(Thread.currentThread().getName()
                        + " increasing the value of the counter");
                Thread.sleep(2000);
                count = count + 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                System.out.println(Thread.currentThread().getName() +
                        " releasing the lock");
                lock.unlock();
            }
        }
    }

}
