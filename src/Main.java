public class Main {
    public static void main(String[] args) {

        System.out.println("Inside: " + Thread.currentThread().getName());

        threadSleep();


    }

    static void threadExtend() {
        Thread thread = new ThreadExample();

        System.out.println("Starting thread");
        thread.start();
    }

    static void threadRunnable() {
        Runnable runnable = new RunnableEx();

        Thread runnableThread = new Thread(runnable);
        System.out.println("Starting runnable thread");
        runnableThread.start();
    }

    static void threadLambda() {

        Runnable runnableLambda = () ->
                System.out.println("Inside : " + Thread.currentThread().getName());

        Thread runnableThreadLambda = new Thread(runnableLambda);
        System.out.println("Starting runnable thread lambda");
        runnableThreadLambda.start();

    }

    static void threadSleep() {

        Runnable runnable = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
            System.out.println("Sleeping for 500ms");
            try {
                Thread.sleep(2000);
                System.out.println("Running after sleeping");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Thread is back 1");
        System.out.println("Thread is back 2");
        System.out.println("Thread is back 3");
    }
}