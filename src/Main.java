public class Main {
    public static void main(String[] args) {

        System.out.println("Inside: " + Thread.currentThread().getName());

        Thread thread = new ThreadExample();

        System.out.println("Starting thread");
        thread.start();

        Runnable runnable = new RunnableEx();

        Thread runnableThread = new Thread(runnable);
        System.out.println("Starting runnable thread");
        runnableThread.start();

        Runnable runnableLambda = () ->
                System.out.println("Inside : " + Thread.currentThread().getName());

        Thread runnableThreadLambda = new Thread(runnableLambda);
        System.out.println("Starting runnable thread lambda");
        runnableThreadLambda.start();

    }
}