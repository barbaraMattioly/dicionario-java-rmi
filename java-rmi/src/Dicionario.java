import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Dicionario extends Remote {

	public String consultar(String palavra) throws java.rmi.RemoteException;

    public void adicionar(String palavra, String significado) throws java.rmi.RemoteException;

    public void remover(String palavra) throws java.rmi.RemoteException;

}