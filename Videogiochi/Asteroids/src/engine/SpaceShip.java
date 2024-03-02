package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JLabel;

public class SpaceShip extends JLabel {

    private int xCoord;
    private int yCoord;
    private Orientation orientation = Orientation.STILL;
    private Laser bullet = new Laser();
    private boolean canShoot = true;
    private LaserTimer timer = new LaserTimer(GameUtils.BULLET_TIMER, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            canShoot = true;
        }    
    });
    
    public SpaceShip(){
        super();
        super.setIcon(GameUtils.SPACESHIP_ICON);
        spawn();
    }

    public void move(){
        int distance = GameUtils.SPACESHIP_SPEED * orientation.getMultiplier();
        if(xCoord+distance >=0 && xCoord+distance <= GameUtils.WIDTH-GameUtils.SPACESHIP_WIDTH)
            xCoord += distance;
        this.setLocation(xCoord, yCoord);

        bullet.move();
        
    }
    private void spawn(){
        xCoord = GameUtils.WIDTH / 2 - GameUtils.SPACESHIP_WIDTH;
        yCoord = GameUtils.HEIGHT - GameUtils.SPACESHIP_HEIGHT - 75;
        this.setBounds(xCoord, yCoord, GameUtils.SPACESHIP_WIDTH, GameUtils.SPACESHIP_HEIGHT);
    }

    public boolean checkCollisions(LinkedList<Asteroid> asteroids){
        for(Asteroid asteroid : asteroids)
            if (this.collidesWith(asteroid))
                return true;
        return false;
    }
    private boolean collidesWith(Asteroid asteroid){
        return this.xCoord <= asteroid.getxCoord()+GameUtils.ASTEROID_SIZE/2 && this.xCoord >= asteroid.getxCoord()-GameUtils.ASTEROID_SIZE/2
            && this.yCoord <= asteroid.getyCoord()+GameUtils.ASTEROID_SIZE/2 && this.yCoord >= asteroid.getyCoord()-GameUtils.ASTEROID_SIZE/2;
    }

    public Laser getBullet() {
        return bullet;
    }
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void shoot(){
        if(!canShoot) return;

        canShoot = false;
        timer.start();
        bullet.setup();
        bullet.launch();
    }
    public long calculateReloadBarValue(){
        return (timer.getTime()*100) / GameUtils.BULLET_TIMER;
    }

    public class Laser extends JLabel{
        private int lxCoord;
        private int lyCoord;
        private boolean isFlying;
    
        public Laser(){
            super();
            super.setIcon(GameUtils.BULLET_ICON);
        }
        //TODO FIX il laser spawna un po' piú a destra del centro
        private void setup(){
            lxCoord = xCoord+GameUtils.SPACESHIP_WIDTH/2-18;
            lyCoord = yCoord;
            this.setBounds(lxCoord, lyCoord, GameUtils.BULLET_SIZE, GameUtils.BULLET_SIZE);

            isFlying = false;
            this.setVisible(false);
        }
        private void launch(){
            isFlying = true;
            this.setVisible(true);
        }
        private void move(){
            if(!isFlying) return;

            lyCoord -= GameUtils.LASER_SPEED;
            setLocation(lxCoord, lyCoord);
        }
        public int getLxCoord() {
            return lxCoord;
        }
        public int getLyCoord() {
            return lyCoord;
        }
        public boolean collidesWith(Asteroid asteroid) {
            if(this.lxCoord <= asteroid.getxCoord()+GameUtils.ASTEROID_SIZE/2 && this.lxCoord >= asteroid.getxCoord()-GameUtils.ASTEROID_SIZE/2
            && this.lyCoord <= asteroid.getyCoord()+GameUtils.ASTEROID_SIZE/2 && this.lyCoord >= asteroid.getyCoord()-GameUtils.ASTEROID_SIZE/2){
                setup();
                return true;
            }
            return false;
        }
    }
}
