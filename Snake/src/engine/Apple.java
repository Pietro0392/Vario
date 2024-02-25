package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple implements Drawable{

    private int xCoord;
    private int yCoord;
    private final Random randomGenerator = new Random();

    public Apple(){
        xCoord = randomGenerator.nextInt(GameUtils.xLimit);
        yCoord = randomGenerator.nextInt(GameUtils.yLimit);
    }

    public int getxCoord() {
        return xCoord;
    }
    public int getyCoord() {
        return yCoord;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(
            xCoord*GameUtils.UNIT_SIZE,
            yCoord*GameUtils.UNIT_SIZE,
            GameUtils.UNIT_SIZE,
            GameUtils.UNIT_SIZE);
    }

}
