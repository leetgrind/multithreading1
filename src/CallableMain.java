import java.util.concurrent.*;

public class CallableMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

       lambda();

    }

    public static void lambda() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName()+ " Processing");
            Thread.sleep(100000);
            System.out.println(Thread.currentThread().getName()+ " Processing finished");
            return "Thread completed";
        };

        Future<String> future = executorService.submit(callable);

        System.out.println(Thread.currentThread().getName() + " Callable submitted");
        System.out.println("Main thread is blocked");

        int i = 0;
        while(!future.isDone()) {
            System.out.println("Waiting for task to finish");
            Thread.sleep(200);
            if(i==5) {
                future.cancel(true);
            }
            i++;
        }

        if(future.isCancelled()) {
            System.out.println("Waited too long. Exiting the program");
            executorService.shutdown();
            return;
        }

        String result = future.get();
        System.out.println(Thread.currentThread().getName() + " Future is complete");
        System.out.println("Result: " + result);
        executorService.shutdown();
    }

    public static void anonymous() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName()+ " Processing");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+ " Processing finished");
                return "Thread completed";
            }
        };

        Future<String> future = executorService.submit(callable);

        System.out.println(Thread.currentThread().getName() + " Callable submitted");
        System.out.println("Main thread is blocked");
        String result = future.get();
        System.out.println(Thread.currentThread().getName() + " Future is complete");
        System.out.println("Result: " + result);
        executorService.shutdown();

    }

    static public void callable() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<String> callable = new CallableEx();

        Future<String> future = executorService.submit(callable);

        System.out.println(Thread.currentThread().getName() + " Callable submitted");
        System.out.println("Main thread is blocked");
        String result = future.get();
        System.out.println(Thread.currentThread().getName() + " Future is complete");
        System.out.println("Result: " + result);
        executorService.shutdown();
    }
}
