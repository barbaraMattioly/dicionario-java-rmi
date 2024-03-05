import java.util.ArrayList;

public class DicionarioServant extends java.rmi.server.UnicastRemoteObject implements Dicionario {
 
	private static final long serialVersionUID = 1L;
	private int cont = 0;

    private ArrayList<String> palavras = new ArrayList<String>();
    private ArrayList<String> significados = new ArrayList<String>();


	public DicionarioServant() throws java.rmi.RemoteException {
		super();
	}

    public String consultar(String palavra) throws java.rmi.RemoteException{
        return "Consultando palavra: " + palavra;
    }

    public void adicionar(String palavra, String significado) throws java.rmi.RemoteException{
       palavras.add(palavra); 
       significados.add(significado);     

       System.out.println("Palavra adicionada");
    }

    public void remover(String palavra) throws java.rmi.RemoteException{
        palavras.remove(palavra);
        
        System.out.println("Palavra removida");
    }

}