import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Game extends UnicastRemoteObject implements RemoteGame {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int PLATFORM_WIDTH = 80;
    public Platform platform = new Platform((WIDTH - PLATFORM_WIDTH) / 2, 460, PLATFORM_WIDTH, 20);
    public List<Rectangle> rects = new ArrayList();
    public List<Platform> platforms = new ArrayList();
    public Ball ball = new Ball();
    private boolean start = false;

    public Game() throws RemoteException {
        super();
       // rects.add(platform);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {
                rects.add(new Rectangle(40 + x * 60, y * 20, 55, 15));
            }
        }
    }
    @Override
    public List<Rectangle> getRects() {
        return rects;
    }
    @Override
    public Ball getBall() {
        return ball;
    }
    @Override
    public void move(int shift, int id) {
        platforms.get(id).move(shift);
    }

    public void step() {
        if(start)
            ball.step(rects);
    }
    @Override
    public void start() {
        start = true;
    }

    @Override
    public int register() throws RemoteException {
        var platform = new Platform((WIDTH - PLATFORM_WIDTH) / 2, 460-platforms.size()*20, PLATFORM_WIDTH, 20);
        platforms.add(platform);
        rects.add(platform);
        return platforms.size()-1;
    }
}
