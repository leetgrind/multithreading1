public class Main {
    public static void main(String[] args) {

        System.out.println("Inside: " + Thread.currentThread().getName());

        threadSleep2();


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
    }

    static void threadSleep2() {
        Runnable runnable = () -> {
            for(int i=0; i< 10; i++) {
                System.out.println("Thread name: " + Thread.currentThread().getName() + "-" + i);
                try {
                    Thread.sleep((int)Math.random()*1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            System.out.println(Thread.currentThread().getName() + " finished");
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}