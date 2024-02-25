package engine;

import java.awt.Graphics;

public class BodyPart implements Drawable{
    private int xCoord;
    private int yCoord;

    public BodyPart(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public void incrementX(int qta){
        xCoord+=qta;
    }
    public void incrementY(int qta){
        yCoord+=qta;
    }


    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    public int getxCoord() {
        return xCoord;
    }
    public int getyCoord() {
        return yCoord;
    }

    
    @Override
    public void draw(Graphics g) {
        g.fillRect(
            xCoord*GameUtils.UNIT_SIZE,
            yCoord*GameUtils.UNIT_SIZE,
            GameUtils.UNIT_SIZE,
            GameUtils.UNIT_SIZE);
    }

    
}
