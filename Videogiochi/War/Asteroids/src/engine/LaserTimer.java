package engine;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class LaserTimer extends Timer{

    private long startTime;

    public LaserTimer(int delay, ActionListener listener) {
        super(delay, listener);
    }

    @Override
    public void start(){
        startTime = System.currentTimeMillis();
        super.start();
    }

    public long getTime(){
        return System.currentTimeMillis() - startTime;
    }
    
}
