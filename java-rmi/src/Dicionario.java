import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

// Interface remota
public interface Dicionario extends Remote {

	public String consultar(String palavra) throws RemoteException; //método para consultar palavra

    public String adicionar(String palavra, String significado) throws RemoteException; //método para adicionar palavra

    public String remover(String palavra) throws RemoteException, IOException; //método para remover palavra

}