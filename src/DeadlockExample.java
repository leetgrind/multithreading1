import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {

        DeadlockExample deadlockExample = new DeadlockExample();

        new Thread(deadlockExample::operation1).start();
        new Thread(deadlockExample::operation2).start();
    }

    public void operation1() {
        lock1.lock();
        System.out.println(Thread.currentThread().getName() + " Lock 1 acquired");

        try {
            Thread.sleep(100);
        }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }


        System.out.println(Thread.currentThread().getName() + " trying to acquire lock 2");
        lock2.lock();
        System.out.println(Thread.currentThread().getName() + " Lock 2 acquired");

        System.out.println("Processing operation 1");

        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() {
        lock2.lock();
        System.out.println(Thread.currentThread().getName() + " Lock 2 acquired");
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(Thread.currentThread().getName() + " trying to acquire lock 1");
        lock1.lock();
        System.out.println(Thread.currentThread().getName() + " Lock 1 acquired");

        System.out.println("Processing operation 2");

        lock1.unlock();
        lock2.unlock();
    }



}
