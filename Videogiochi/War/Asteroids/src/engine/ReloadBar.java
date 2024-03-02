package engine;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ReloadBar extends JProgressBar{
    public ReloadBar(){
        super(0,100);
        this.setOrientation(SwingConstants.VERTICAL);
        setForeground(GameUtils.RELOADBAR_COLOR);
        setOpaque(false);
        setBounds(10, GameUtils.HEIGHT*2/3-50, 30, GameUtils.HEIGHT / 3);

        setValue(100);
    }
}
