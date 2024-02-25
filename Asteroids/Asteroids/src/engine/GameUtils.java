package engine;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

public class GameUtils {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final int ASTEROID_SIZE = 50;
    static final int BULLET_SIZE = 35;
    static final int SPACESHIP_WIDTH = 50;
    static final int SPACESHIP_HEIGHT = 75;

    static final int SLEEP_INTERVAL = 30;
    
    static final int LASER_SPEED = 10;
    static final int SPACESHIP_SPEED = 10;

    static final int BULLET_TIMER = 3000;

    static final Color BACKGROUND_COLOR = Color.BLACK;
    static final Color RELOADBAR_COLOR = Color.decode("#448AFF");
    static final Color TEXT_COLOR = Color.WHITE;

    static final Font TEXT_FONT = new Font("MV Boli", Font.BOLD, 30);

    static final ImageIcon ASTEROID_ICON = new ImageIcon("C:\\Users\\39328\\Desktop\\java_srcs\\Asteroids\\src\\icons\\aste.png");
    static final ImageIcon SPACESHIP_ICON = new ImageIcon("C:\\Users\\39328\\Desktop\\java_srcs\\Asteroids\\src\\icons\\spaceship.png");
    static final ImageIcon BULLET_ICON = new ImageIcon("C:\\Users\\39328\\Desktop\\java_srcs\\Asteroids\\src\\icons\\smallBullet.png");
}
