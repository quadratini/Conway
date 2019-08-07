import java.util.ArrayList;
import java.util.Arrays;

// a whirl
public class World {
    int size;
    int[][] tiles;

    public World(int size) {
        this.size = size;

        tiles = new int[][]{
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
        };
    }

    // generates randomized world
    private int[][] createWorld() {
        int[][] tiles = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int rand = (int) (Math.random() * 2);
                tiles[i][j] = rand;
            }
        }
        return tiles;
    }

    // change value of given cell
    public void setCell(int x, int y, int value) {
        tiles[y][x] = value;
    }

    // return given cell
    public int getCell(int[][] tiles, int x, int y) {
        return tiles[y][x];
    }

    // displayBoard
    // why do rows become columns ??
    public void displayWorld() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(tiles[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /*
        1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
        2. Any live cell with two or three live neighbours lives on to the next generation.
        3. Any live cell with more than three live neighbours dies, as if by overpopulation.
        4. Any dead cell with three live neighbours becomes a live cell, as if by reproduction.
     */

    public void life() {
        int[][] resultingWorld = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int liveNeighbors = getLiveNeighbors(tiles, i, j);

                if (isAlive(tiles, i, j) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    makeDead(resultingWorld, i, j);
                } else if (!isAlive(tiles, i, j) && liveNeighbors == 3) {
                    makeAlive(resultingWorld, i, j);
                } else {
                    resultingWorld[j][i] = tiles[j][i];
                }
            }
        }
        tiles = resultingWorld;
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(resultingWorld[i]));
        }
    }

    public void makeDead(int[][] tiles, int i, int j) {
        tiles[j][i] = 0;
    }

    public void makeAlive(int[][] tiles, int i, int j) {
        tiles[j][i] = 1;
    }

    public boolean isAlive(int[][] tiles, int x, int y) {
        return (getCell(tiles, x, y) == 1);
    }

    // Gets the values around the given cell
    public int getLiveNeighbors(int[][] tiles, int cellX, int cellY) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        int liveNeighbors = 0;

        // Top left
        if (cellY - 1 >= 0 && cellX - 1 >= 0) {
            neighbors.add(tiles[cellY - 1][cellX - 1]);
        }
        // Top
        if (cellY - 1 >= 0) {
            neighbors.add(tiles[cellY - 1][cellX]);
        }
        // Top right
        if (cellY - 1 >= 0 && cellX + 1 <= size - 1) {
            neighbors.add(tiles[cellY - 1][cellX + 1]);
        }

        // Left
        if (cellX - 1 >= 0) {
            neighbors.add(tiles[cellY][cellX - 1]);
        }
        // Right
        if (cellX + 1 <= size - 1) {
            neighbors.add(tiles[cellY][cellX + 1]);
        }

        // Bottom left
        if (cellY + 1 <= size - 1 && cellX - 1 >= 0) {
            neighbors.add(tiles[cellY + 1][cellX - 1]);
        }
        // Bottom
        if (cellY + 1 <= size - 1) {
            neighbors.add(tiles[cellY + 1][cellX]);
        }
        // Bottom right
        if (cellX + 1 <= size - 1 && cellY + 1 <= size - 1) {
            neighbors.add(tiles[cellY + 1][cellX + 1]);
        }

        for (int i =  0; i < neighbors.size(); i++) {
            int value = neighbors.get(i);
            if (value == 1) {
                liveNeighbors += 1;
            }
        }
        return liveNeighbors;
    }
}
