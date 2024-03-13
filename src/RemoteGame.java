import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteGame extends Remote {
    List<Rectangle> getRects() throws RemoteException;

    Ball getBall() throws RemoteException;

    void move(int shift, int id) throws RemoteException;

    void start() throws RemoteException;

    int register() throws RemoteException;
}
