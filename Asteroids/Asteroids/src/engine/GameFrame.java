package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements KeyListener{

    private LinkedList<Asteroid> asteroids = new LinkedList<>();
    private SpaceShip spaceship = new SpaceShip();
    private ReloadBar reloadBar = new ReloadBar();
    private GameScore gameScore = new GameScore();

    public GameFrame(){
        super("Asteroids Game");
        this.setFocusable(true);
        this.setSize(GameUtils.WIDTH,GameUtils.HEIGHT);
        this.getContentPane().setBackground(GameUtils.BACKGROUND_COLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.addKeyListener(this);
        this.add(reloadBar);
        this.add(spaceship);
        this.add(gameScore);
        this.add(spaceship.getBullet());

        startGame();
    }

    private void startGame(){

        for(int i=0;i<12;i++){
            Asteroid asteroid = new Asteroid();
            asteroids.add(asteroid);
            this.add(asteroid);
        }

        this.setVisible(true);

        while(!endOfGame())
            runGame();

        gameScore.evaluate("Game Over: ");
    }

    private void runGame(){

        spaceship.move();
        gameScore.evaluate("Seconds: ");
        reloadBar.setValue((int)spaceship.calculateReloadBarValue());
        //TODO obsolete: migliorabile? non penso vada avanti ma penso faccia da capo per cui asteroids.get(i) diventa O(n) ogni volta...
        //TODO edit fix: per come ho scritto il programma basta fare un vettore con capacita' iniziale 12 e la performance migliorerebbe parecchio
        for(int i=0;i<asteroids.size();i++){
                Asteroid asteroid = asteroids.get(i);
                if(asteroid.checkEnd() || spaceship.getBullet().collidesWith(asteroid))
                    asteroid.respawn();
                else asteroid.fall();
            }
        Gamewait();
    }
    private void Gamewait(){
        try {
            Thread.sleep(GameUtils.SLEEP_INTERVAL);
        } catch (InterruptedException e) {
            System.err.println("Errore Thread principale");
            e.printStackTrace();
        }
    }
    private boolean endOfGame(){
        return spaceship.checkCollisions(asteroids);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!endOfGame())
            switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                    spaceship.setOrientation(Orientation.LEFT);
                    break;
                case KeyEvent.VK_D:
                    spaceship.setOrientation(Orientation.RIGHT);
                    break;
                case KeyEvent.VK_SPACE:
                    spaceship.shoot();
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        spaceship.setOrientation(Orientation.STILL);
    }
}
