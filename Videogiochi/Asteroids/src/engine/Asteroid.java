package engine;

import java.util.Random;

import javax.swing.JLabel;

public class Asteroid extends JLabel {

    private int xCoord;
    private int yCoord;

    public Asteroid(){
        super();
        super.setIcon(GameUtils.ASTEROID_ICON);
        this.spawn();
    }

    public int getxCoord() {
        return xCoord;
    }
    public int getyCoord() {
        return yCoord;
    }

    private void spawn(){
        Random randomGenerator = new Random();
        xCoord = randomGenerator.nextInt(GameUtils.WIDTH-GameUtils.ASTEROID_SIZE);
        yCoord = randomGenerator.nextInt(GameUtils.HEIGHT/10*6);
        this.setBounds(xCoord, yCoord, GameUtils.ASTEROID_SIZE, GameUtils.ASTEROID_SIZE);
    }
    public void respawn(){
        spawn();
    }
    
    public void fall(){
        yCoord+=5;
        this.setLocation(xCoord, yCoord);
    }

    public boolean checkEnd(){
        return this.yCoord+GameUtils.ASTEROID_SIZE >= GameUtils.HEIGHT - GameUtils.SPACESHIP_HEIGHT;
    }
}
