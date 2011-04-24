package aerogui;

import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SplashTask extends TimerTask {
    private static final Integer NUM_OF_SPLASH = 4;
    private static final String SPLASH_PATH = "/images/splash";
    private static final String SPLASH_EXT = ".png";

    private JLabel _label;
    private Integer _index = 1;

    SplashTask(JLabel label) {
        this._label = label;
    }

    public void run() {
        if (_index > NUM_OF_SPLASH) {
            _index = 1;
        }
        Icon icon = new ImageIcon(getClass().getResource(
                SPLASH_PATH + Integer.toString(_index) + SPLASH_EXT));
        _label.setIcon(icon);
        _index++;
    }
}