import java.rmi.Naming;

public class DicionarioServer {

	public DicionarioServer() {
		try {
			Dicionario c = new DicionarioServant();
			Naming.rebind("rmi://localhost/DicionarioService", c);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new DicionarioServer();
		System.out.println("Servidor do Dicionario em execução.");
	}
}