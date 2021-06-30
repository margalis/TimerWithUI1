package lockInterfaceUsages.lockconditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ItemQueue {
    private Object[] items = null;
    private int current = 0;
    private int placeIndex = 0;
    private int removeIndex = 0;

    private final Lock lock;
    private final Condition isEmpty;
    private final Condition isFull;

    public ItemQueue(int capacity) {
        this.items = new Object[capacity];
        lock = new ReentrantLock();
        isEmpty = lock.newCondition();
        isFull = lock.newCondition();
    }

    public void add(Object item) throws InterruptedException {
        lock.lock();

        while (current >= items.length)
            isFull.await();

        items[placeIndex] = item;
        placeIndex = (placeIndex + 1) % items.length;
        ++current;

        //Notify the consumer that there is data available.
        isEmpty.signal();
        lock.unlock();
    }

    public Object remove() throws InterruptedException {
        Object item = null;

        lock.lock();

        while (current <= 0) {
            isEmpty.await();
        }
        item = items[removeIndex];
        removeIndex = (removeIndex + 1) % items.length;
        --current;

        //Notify the producer that there is space available.
        isFull.signal();
        lock.unlock();

        return item;
    }

    public boolean isEmpty() {
        return (items.length == 0);
    }
}

