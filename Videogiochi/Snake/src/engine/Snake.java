package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Snake implements Drawable{

    private final Vector<BodyPart> bodyParts = new Vector<>(5);
    private int numberOfBodyParts = 5;
    private Direction snakeOrientation;
    private final BodyPart head;

    public Snake(){
        
        // inizialmente va a destra
        snakeOrientation = Direction.RIGHT;

        head = new BodyPart(4,0);
        // head si trova all'indice zero cosi'
        // da poter essere comoda per essere usata
        bodyParts.add(head);

        // inizializzo con altri 4 pezzi oltre alla testa
        for(int i=3;i>=0;i--)
            bodyParts.add(new BodyPart(i, 0));

    }
    /* TODO bug fix:
    se si cambia troppo velocemente la direzione da tastiera, (basta premere
    due tasti contemporaneamente per notarlo)
    puo' terminare la partita, perche' viene considerato come se la testa
    toccasse una altra parte del corpo, e' possibile che sia dovuto a Thread.sleep()
    motivo per cui sarebbe meglio implementare un timer (vedi TODO in SnakePanel) */
    public boolean collides(){
        
        // controlla se tocca un estremo

        if(!(head.getxCoord()>=0 && head.getxCoord()<GameUtils.xLimit
        && head.getyCoord()>=0 && head.getyCoord()<GameUtils.yLimit))
            return true;
        
        // controlla se la testa tocca il corpo

        for(int i=1;i<numberOfBodyParts;i++){
            if(bodyParts.get(i).getxCoord() == head.getxCoord()
            && bodyParts.get(i).getyCoord() == head.getyCoord())
                return true;
        }

        return false;

    }

    public boolean eatsApple(Apple gameApple){
        return (head.getxCoord() == gameApple.getxCoord()
        && head.getyCoord() == gameApple.getyCoord());
    }

    public void move(){

        for(int i=numberOfBodyParts-1;i>0;i--){
            //TODO ottimizzabile con delle variabili + memoria ma anche + veloce
            bodyParts.get(i).setxCoord(bodyParts.get(i-1).getxCoord());
            bodyParts.get(i).setyCoord(bodyParts.get(i-1).getyCoord());
        }

        switch(snakeOrientation){
            case UP:
                head.incrementY(-1);
                break;
            case DOWN:
                head.incrementY(1);
                break;
            case LEFT:
                head.incrementX(-1);
                break;
            case RIGHT:
                head.incrementX(1);
                break;
        }
    }

    public void setSnakeOrientation(Direction snakeOrientation) {
        this.snakeOrientation = snakeOrientation;
    }
    public Direction getSnakeOrientation() {
        return snakeOrientation;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.setColor(Color.GREEN);
        for(BodyPart bodyComponent : bodyParts)
            bodyComponent.draw(g);
    }

}
