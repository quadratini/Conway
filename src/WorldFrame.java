import javax.swing.*;
import java.awt.*;

// extends jpanel
public class WorldFrame extends JPanel {
    public static final Color ALIVE = new Color(0,0,153);
    public static final Color DEAD = new Color(255,255,255);
    public static final int GRID_SIZE = 50;

    public final int SIZE;
    private final Color[][] tiles;
    public WorldFrame(World world) {
        this.SIZE = world.size;
        this.tiles = new Color[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (world.isAlive(world.tiles, i, j)) {
                    this.tiles[i][j] = ALIVE;
                } else {
                    this.tiles[i][j] = DEAD;
                }
            }
        }
        int prefWidth = SIZE * GRID_SIZE;
        int prefHeight = SIZE * GRID_SIZE;
        setPreferredSize(new Dimension(prefWidth, prefHeight));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());
        int rectWidth = getWidth() / SIZE;
        int rectHeight = getHeight() / SIZE;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int x = i * rectWidth;
                int y = j * rectHeight;
                Color terrainColor = tiles[i][j];
                g.setColor(terrainColor);
                g.fillRect(x, y, rectWidth, rectHeight);
            }
        }
    }
}
