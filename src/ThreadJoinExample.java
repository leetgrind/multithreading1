public class ThreadJoinExample {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable1 = () -> {
            System.out.println("Runnable 1");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread 1 complete");
        };

        Runnable runnable2 = () -> {
            System.out.println("Runnable 2");

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread 2 complete");
        };

        System.out.println("Starting thread 1");
        Thread thread1 = new Thread(runnable1);
        thread1.start();

        thread1.join(1000);

        System.out.println("Starting thread 2");
        Thread thread2 = new Thread(runnable2);

        thread2.start();




    }

}
