public class ThreadExample extends Thread{
    @Override
    public void run() {
        System.out.println("Inside: " + Thread.currentThread().getName());
    }
}
