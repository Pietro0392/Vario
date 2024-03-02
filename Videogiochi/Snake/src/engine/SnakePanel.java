package engine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakePanel extends JPanel {
    private final Snake gameSnake = new Snake();
    private Apple gameApple = new Apple();
    private final Score gameScore;

    public SnakePanel() {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch(key){
                    case KeyEvent.VK_W:
                        if(gameSnake.getSnakeOrientation()!=Direction.DOWN)
                        gameSnake.setSnakeOrientation(Direction.UP);
                        break;
                    case KeyEvent.VK_A:
                        if(gameSnake.getSnakeOrientation()!=Direction.RIGHT)
                        gameSnake.setSnakeOrientation(Direction.LEFT);
                        break;
                    case KeyEvent.VK_S:
                        if(gameSnake.getSnakeOrientation()!=Direction.UP)
                        gameSnake.setSnakeOrientation(Direction.DOWN);
                        break;
                    case KeyEvent.VK_D:
                        if(gameSnake.getSnakeOrientation()!=Direction.LEFT)
                        gameSnake.setSnakeOrientation(Direction.RIGHT);
                        break;
                }
            }
        });
        gameScore = new Score();
    }

    @Override
    public void paint(Graphics g) {

            if(gameSnake.collides())
                //TODO gestire la fine della partita
                return;

            if(gameSnake.eatsApple(gameApple)){
                gameScore.increment();
                gameApple = new Apple();
            }

            super.paint(g);
            gameSnake.draw(g);
            gameApple.draw(g);
            gameScore.draw(g);

            try {
                this.repaint();
                // TODO sostituire con un timer vero
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.err.println("Errore con Thread sleep");
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        frame.setSize(GameUtils.WIDTH, GameUtils.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SnakePanel());
        frame.setVisible(true);
    }
}
