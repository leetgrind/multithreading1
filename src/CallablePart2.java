import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallablePart2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        invokeAll();
    }

    static void invokeAll() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> callable1 = () -> {
            Thread.sleep(1000);
            return "Callable 1 finished";
        };

        Callable<String> callable2 = () -> {
            Thread.sleep(2000);
            return "Callable 2 finished";
        };

        Callable<String> callable3 = () -> {
            Thread.sleep(3000);
            return "Callable 3 finished";
        };

        List<Future<String>> futureList =
                executorService.invokeAll(Arrays.asList(callable1, callable2, callable3));

        for (var future: futureList) {
            System.out.println(future.get());
        }

        executorService.shutdown();
    }
}
