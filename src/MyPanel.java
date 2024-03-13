import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class MyPanel extends JPanel {
    private RemoteGame game;

    //public int x = (WIDTH - PLATFORM_WIDTH) / 2;

    public MyPanel(RemoteGame game) {
        this.game = game;
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }

    @Override
    public void paint(Graphics g) {
        try {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
            g.setColor(Color.BLACK);
            for (Rectangle rectangle : game.getRects()) {
                if (rectangle.isAlive())
                    drawRectangle(g, rectangle);
            }
            g.setColor(Color.BLACK);
            Ball ball = game.getBall();
            g.fillOval(ball.x - ball.r, ball.y - ball.r, ball.r * 2, ball.r * 2);
        } catch (RemoteException e) {
        }
    }

    private void drawRectangle(Graphics g, Rectangle rectangle) {
        g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //RemoteGame game = new Game();
        Registry registry = LocateRegistry.getRegistry(1099);
        RemoteGame game = (RemoteGame) registry.lookup("//localhost/Game");
        var id = game.register();
        var panel = new MyPanel(game);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    game.start();
                    if (e.getKeyCode() == KeyEvent.VK_LEFT)
                        game.move(-15, id);
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                        game.move(15, id);
                    panel.invalidate();
                    panel.repaint();
                } catch (RemoteException re) {
                }
            }
        });
        new Timer(true).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                panel.invalidate();
                panel.repaint();
            }
        }, 0, 33);
    }
}
