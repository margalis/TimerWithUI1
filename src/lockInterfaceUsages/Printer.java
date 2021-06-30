package lockInterfaceUsages;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {
    ReentrantLock r = new ReentrantLock();

    public void print(String name ){
        try {
            r.tryLock(2, TimeUnit.SECONDS);
            for (int i = 0; i <10 ; i++) {
                System.out.println(name);
                    TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void printWithComas(String name){
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(name+", ");
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void finallyUnlock(){
        r.unlock();
    }

    }
