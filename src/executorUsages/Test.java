package executorUsages;

import java.util.Date;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Test {
    public static void main(String[] args) {
        //ScheduldTHreadpools

        Runnable runnable= ()-> System.out.println("task is running");
        //Callable<String> callable = Test::function2;
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        /*service.schedule(runnable,2, TimeUnit.SECONDS);
        service.schedule(callable,1,TimeUnit.MILLISECONDS);*/

        //service fixed rate -vy  nayum a tvyal threadi  nakhord executiony erb a sksel, dranic fixvats jam
        //heto eli a ed thready executum

        //service at fixed delay y  nayum a tvyal threadi executiony erb a verjacel ancats angam,
        //ed pahic fixvats vakht heto eli  execute a anum
        //but I have a problem with this one
        //chi tpum :/
        service.scheduleAtFixedRate(Test::function2, 1, 3L , SECONDS);
        service.scheduleWithFixedDelay(Test::function3, 2, 3L , SECONDS);
       // service.shutdown();


        //Cached Thread Pool
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i <10 ; i++) {
            executorService.submit(Test::function2);
        }
        executorService.shutdown();

        //Completable future
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> "Hello");


    }
    private static void  function2(){

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HIIII2");
        //return "Hi2";
    }
    private static String function3(){

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HI3333");
        return "Hi3";
    }

}