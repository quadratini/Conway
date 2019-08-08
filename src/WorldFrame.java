import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;

// Things to do: finish assignments
// understand what is going on as you do it
// understand what you DONT understand.
// UNDASTAN

public class WorldFrame extends JPanel {
    private static final Color ALIVE = new Color(0,0,0);
    private static final Color DEAD = new Color(255,255,255);
    private static final int GRID_SIZE = 5;

    public World world;
    public final int SIZE;

    boolean paused = false;
    // WorldFrame constructor
    public WorldFrame(World world) {
        this.world = world;
        SIZE = world.getSize();
        InputMap inputMap = getInputMap(WHEN_FOCUSED);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "onSpace");
        actionMap.put("onSpace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paused = !paused;
            }
        });

        int prefWidth = SIZE * GRID_SIZE;
        int prefHeight = SIZE * GRID_SIZE;
        setPreferredSize(new Dimension(prefWidth, prefHeight));
    }

    // draws tile colors in passed in position...?
    public void drawWorld(Graphics g) {
        int[][] tiles = world.getTiles();
        int rectWidth = getWidth() / SIZE;
        int rectHeight = getHeight() / SIZE;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int x = i * rectWidth;
                int y = j * rectHeight;
                if (world.isAlive(tiles, i, j)) {
                    g.setColor(ALIVE);
                } else {
                    g.setColor(DEAD);
                }
                g.fillRect(x, y, rectWidth, rectHeight);
            }
        }
    }

    // I think this draws the world. Need to figure that out.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());

        drawWorld(g);
    }
}
