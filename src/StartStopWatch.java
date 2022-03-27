// Start Stop Watch with if/else statement
// Author: Kamran Rashidov
// Date: 15.09.2021

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartStopWatch implements ActionListener {
    // instance variables
    JFrame frame1 = new JFrame();
    JButton startStopButton = new JButton();
    JButton resetButton = new JButton();
    JLabel timeLabel = new JLabel();
    ImageIcon imageIcon1 = new ImageIcon("wall-clock.png");

    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false; // if timer has started or not
    // placeholder in order to hold zeros in time format
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    ActionListener al = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds++;
            if (seconds >= 59)
            {
                seconds = 0;
                minutes++;
            }
            else if (minutes >= 59)
            {
                seconds = 0;
                minutes = 0;
                hours++;
            }

            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            // update label
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    };

    // repeat action every 1000 milliseconds (1 second)
    Timer timer1 = new Timer(1000, al);

    // constructor of StopWatch class
    public StartStopWatch() {

        // get size of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double widthOfScreen = screenSize.getWidth();
        double heightOfScreen = screenSize.getHeight();

        // coordinates of frame in the center of screen
        int xLoc = (((int)widthOfScreen / 2) - (300/2));
        int yLoc = (((int)heightOfScreen / 2) - (200/2));

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setTitle(" Stopwatch");
        frame1.setSize(250,150);
        frame1.setLocation(xLoc, yLoc);
        frame1.getContentPane().setBackground(new Color(70,140,250));
        frame1.setIconImage(imageIcon1.getImage());
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setVisible(true); // setVisible should be always at the end!


        // timelabel settings
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(38,10, 160, 50);
        Font timeLabelFont = new Font("Verdana", Font.PLAIN, 25);
        timeLabel.setFont(timeLabelFont);
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true); // paints every pixel within its bounds.
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        frame1.add(timeLabel);

        // startstopbutton settings
        startStopButton.setBounds(38,65,75,35);
        Font buttonFont = new Font("Ink free", Font.PLAIN,12);
        startStopButton.setFont(buttonFont);
        startStopButton.setBackground(new Color(70,250,70));
        startStopButton.addActionListener(this);
        startStopButton.setText("START");
        startStopButton.setFocusable(false);
        frame1.add(startStopButton);

        // reset button
        resetButton.setBounds(125,65,75,35);
        resetButton.setBackground(new Color(250,190,70));
        resetButton.setFont(buttonFont);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        resetButton.setText("RESET");
        frame1.add(resetButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startStopButton){
            start();

            if(started == false){
                startStopButton.setText("STOP");
                startStopButton.setBackground(new Color(250,70,70));
                started = true;
                start();

            }
            else{
                startStopButton.setText("START");
                startStopButton.setBackground(new Color(70,250,70));
                started = false;
                stop();

            }
        }
        else if (e.getSource() == resetButton){
            reset();
            startStopButton.setText("START");
            startStopButton.setBackground(new Color(70,250,70));
            started = false;
        }

    }

    // start method for time
    public void start(){
        timer1.start();
    }

    // stop method
    public void stop(){
        timer1.stop();
    }

    // reset method
    public void reset(){
        timer1.stop();
        elapsedTime = 0;
        hours = 0;
        minutes = 0;
        seconds = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

}
