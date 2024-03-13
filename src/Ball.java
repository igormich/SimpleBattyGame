import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Ball implements Serializable {
    protected int r = 8;
    protected int x = Game.WIDTH / 2;
    protected int y = Game.HEIGHT - 100;
    protected int sx = 5;
    protected int sy = -5;

    public void step(List<Rectangle> rects) {
        if (x + sx < 0)
            sx = -sx;
        if (x + sx > Game.WIDTH)
            sx = -sx;
        if (y + sy < 0)
            sy = -sy;
        for(Rectangle rect:rects) {
            if(rect.isDead())
                continue;
            var rectangle = new java.awt.Rectangle(rect.getX()-r, rect.getY()-r, rect.getWidth()+r*2, rect.getHeight()+r*2);
            if (rectangle.contains(new Point(x+sx,y))) {
                rect.touch();
                sx = -sx;
            }
            if (rectangle.contains(new Point(x,y+sy))) {
                rect.touch();
                sy = -sy;
            }
        }
        x += sx;
        y += sy;


    }
}
