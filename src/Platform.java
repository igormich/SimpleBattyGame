import java.io.Serializable;

public class Platform extends Rectangle {
    public Platform(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move(int shift) {
        x += shift;
        if (x < 0)
            x = 0;
        if (x > Game.WIDTH - width)
            x = Game.WIDTH - width;
    }
    @Override
    public void touch() {

    }
}
