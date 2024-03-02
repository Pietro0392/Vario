package engine;

import javax.swing.JTextField;

public class GameScore extends JTextField{

    private long initialTime;

    public GameScore(){
        super();
        initialTime = System.currentTimeMillis();
        setBounds(GameUtils.WIDTH/8, GameUtils.HEIGHT/8, GameUtils.WIDTH, 50);
        setFont(GameUtils.TEXT_FONT);
        setForeground(GameUtils.TEXT_COLOR);
        setBorder(null);
        setEditable(false);
        setFocusable(false);
        setOpaque(false);
    }
    public void evaluate(String message){
        setText(message + (System.currentTimeMillis()-initialTime)/1000);
    }
}
