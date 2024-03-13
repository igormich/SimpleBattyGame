import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Game game = new Game();
        var registry = LocateRegistry.createRegistry(1099);
        registry.rebind("//localhost/Game", game);

        new Timer(true).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                game.step();
            }
        }, 0, 33);
    }
}
