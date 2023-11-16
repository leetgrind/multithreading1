import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable runnable = () -> {
            System.out.println("Inside thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
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

        if(!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }

    }
}
