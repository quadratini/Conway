import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Conway {
    private static final int SIZE = 150;
    public static void main(String[] args) {
        World ronpobWorld = new World(SIZE);
        Timer timer = new Timer();
        JFrame frame = new JFrame("WHIRL");
        WorldFrame worldFrame = new WorldFrame(ronpobWorld);

        frame.add(worldFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!worldFrame.paused) {
                    ronpobWorld.life();
                    worldFrame.repaint();
                }
            }
        }, 0, 100);
    }
}
