import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPool {

    public static void main(String[] args) {
        // execute task periodically
        // execute after delay
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(1);

        Runnable task = () ->
                System.out.println("Current time: " + System.currentTimeMillis());


        System.out.println("Submission time " + System.currentTimeMillis());

        scheduledExecutorService.schedule(task, 500, TimeUnit.MILLISECONDS);


        scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

        //scheduledExecutorService.shutdown();
    }
}
