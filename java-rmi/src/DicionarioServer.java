import java.rmi.Naming;

public class DicionarioServer {

	public DicionarioServer() {
		try {
			Dicionario d = new DicionarioServant(); //instancia o objeto remoto
			Naming.rebind("rmi://localhost/DicionarioService", d); //registra o objeto remoto
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new DicionarioServer(); //inicia o servidor
		System.out.println("Servidor do Dicionario em execução.");
	}
}