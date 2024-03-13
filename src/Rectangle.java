import java.io.Serializable;

public class Rectangle implements Serializable {
    protected int x;
    private final int y;
    protected int width;
    private final int height;
    private boolean alive = true;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public boolean isDead() {
        return !alive;
    }
    public boolean isAlive() {
        return alive;
    }

    public void touch() {
        alive = false;
    }

}
