package lockInterfaceUsages;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterThread implements Runnable {

    private static Lock lock = new ReentrantLock(true); //fairness on
    private Printer printer ;
    private String name ;

    public PrinterThread(Printer printer, String name) {
        this.printer = printer;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " running");
           printer.printWithComas(name);
            System.out.println(Thread.currentThread().getName() + " finished");
        } catch(InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        } finally {
            lock.unlock();
        }
    }
}
