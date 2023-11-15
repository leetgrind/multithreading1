public class Main {
    public static void main(String[] args) {

        System.out.println("Inside: " + Thread.currentThread().getName());

        Thread thread = new ThreadExample();

        System.out.println("Starting thread");
        thread.start();
    }
}