public class MemoryError {

    private static volatile boolean bool = false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {

            while (!bool) {

            }

            System.out.println("Out of the loop");

            while(bool) {

            }

            System.out.println("Out of the second loop");
        });

        thread.start();
        Thread.sleep(1000);

        System.out.println("Out of the sleep");
        bool = true;

        Thread.sleep(1000);
        System.out.println("Out of second sleep");
        bool = false;
    }
}
