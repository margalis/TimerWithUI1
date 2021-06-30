package lockInterfaceUsages;

public class Test {
    public static void main(String[] args) {
        //trylock-ovy
        Printer printer = new Printer();
        /*Thread tryT1= new Thread(new Runnable() {
            @Override
            public void run() {
                printer.print("tryT1");
                printer.finallyUnlock();
            }
        });
        Thread tryT2= new Thread(new Runnable() {
            @Override
            public void run() {
                printer.print("tryT2");
                printer.finallyUnlock();
            }
        });

        tryT1.start();
        tryT2.start();*/

        //interupt lock-ovy
        Thread t1I= new Thread(new PrinterThread(printer,"T0 I"));
        Thread t2I= new Thread(new PrinterThread(printer,"T1 I"));

        t1I.start();
        t2I.start();
        t1I.interrupt();
    }
}
