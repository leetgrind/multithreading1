import java.util.concurrent.Callable;

public class CallableEx implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+ " Processing");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+ " Processing finished");
        return "Thread completed";
    }
}
