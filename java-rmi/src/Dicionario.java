import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Dicionario extends Remote {

	public String consultar(String palavra) throws RemoteException;

    public String adicionar(String palavra, String significado) throws RemoteException;

    public String remover(String palavra) throws RemoteException;

}