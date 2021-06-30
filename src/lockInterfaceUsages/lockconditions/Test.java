package lockInterfaceUsages.lockconditions;

public class Test {
    private static void main(String[] args) throws InterruptedException {
        ItemQueue itemQueue = new ItemQueue(10);

        //Create a producer and a consumer.
        Thread producer = new Producer(itemQueue);
        Consumer consumer = new Consumer(itemQueue);

        //Start both threads.
        producer.start();
        consumer.start();

        //Wait for both threads to terminate.
        producer.join();
        consumer.join();
    }

}
