import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable runnable = () -> {
            System.out.println("Inside thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 1 finished");
        };


        executorService.submit(runnable);

        Runnable runnable2 = () ->
                System.out.println("Inside thread " + Thread.currentThread().getName());

        executorService.submit(runnable2);

        executorService.shutdown();

    }
}
