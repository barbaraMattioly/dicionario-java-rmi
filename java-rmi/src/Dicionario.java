import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Dicionario extends Remote {

	public String consultar(String palavra) throws RemoteException;

    public void adicionar(String palavra, String significado) throws RemoteException;

    public void remover(String palavra) throws RemoteException;

}