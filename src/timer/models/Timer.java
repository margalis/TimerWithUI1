package timer.models;

import timer.TimerUI;

import java.util.concurrent.locks.ReentrantLock;

public class Timer {
    public static volatile int flag;
    private int second;
    private int minute;

    public Timer(int second, int minute) {
        this.second = second;
        this.minute = minute;
    }
    public Timer(){}

    public void setSecond(int second) {
        this.second = second;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public  void TimerF() {
        flag=0 ;
        for (int j = minute; j >= 0; --j) {
            for (int i = (j == minute) ? second : 59; i >= 0; --i) {
                TimerUI.timer.setText(((j < 10) ? ("0" + j) : j)
                        + ":" + ((i < 10) ? ("0" + i) : i));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
       TimerUI.timer.setText("Time's up");
    }

}
