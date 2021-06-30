package timer;

import timer.models.Timer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerUI implements ActionListener {
    static int minute, second ;
    static  boolean is;
    private JFrame fr;
    private JPanel main;
    private JLabel info;
    private JComboBox<String> minutes, seconds;

    private JButton StartButton;  // for start
     //private JButton StopButton;  //for stop

    public static JLabel timer;

    TimerUI() {
        fr = new JFrame();

        main = new JPanel();

        info = new JLabel("Enter minute and second");
        JLabel justexternal1 = new JLabel("------------------------");
        JLabel justexternal = new JLabel("--------------------------");
        timer = new JLabel("00:00");


        minutes = new JComboBox<String>(); // ?
        seconds = new JComboBox<String>();

        for (int i = 0; i < 60; ++i) {
            if (i < 10) {
                minutes.addItem("0" + i);
                seconds.addItem("0" + i);
            } else {
                minutes.addItem("" + i);
                seconds.addItem("" + i);
            }
        }

        StartButton = new JButton("Start");
      //  StopButton = new JButton("Stop");

        main.add(info);
        main.add(minutes);
        main.add(seconds);
        main.add(justexternal1);
        main.add(StartButton);
       //  main.add(StopButton);
        main.add(justexternal);
        main.add(timer);
        StartButton.addActionListener(this);
       // StopButton.addActionListener(this);

        fr.add(main);
        fr.setSize(300, 150);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.models.Timer timerObject = new Timer();
        if (e.getSource() == StartButton && !is) {
           // StartButton.setText("Staaart");
            // StopButton.setText("Stop");
            is=true;
            minute = Integer.parseInt(String.valueOf(minutes.getSelectedItem()));
            second = Integer.parseInt(String.valueOf(seconds.getSelectedItem()));
            timerObject.setMinute(minute);
            timerObject.setSecond(second);
            Thread t = new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    timerObject.TimerF();
                    is=false;
                }
            });
            t.start();

        }
        else if(e.getSource() == StartButton && is){

        }
        /*else if (e.getSource() == StopButton && Timer.flag==0) {
           *//* Timer.flag=1;
            StartButton.setText("Restart");
            StopButton.setText("Resume");*//*
        }
        else if (e.getSource() == StopButton && Timer.flag==1) {
           // Timer.flag=0;

        }*/
        else {
            JOptionPane.showMessageDialog(fr, "Something is wrong. Try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
            //System.exit(0);
        }
    }
}
