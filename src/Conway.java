import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Conway {
    public static final int SIZE = 10;
    public static void main(String[] args) {
        World ronpobWorld = new World(SIZE);

        Timer timer = new Timer();
        ronpobWorld.displayWorld();

        JFrame frame = new JFrame("WHIRL");
        WorldFrame worldFrame = new WorldFrame(ronpobWorld);
        frame.add(worldFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                frame.remove(worldFrame);
                ronpobWorld.life();
                ronpobWorld.displayWorld();
                WorldFrame worldFrame = new WorldFrame(ronpobWorld);
                frame.add(worldFrame);
                frame.setVisible(true);
            }
        }, 0, 200);

    }
}
