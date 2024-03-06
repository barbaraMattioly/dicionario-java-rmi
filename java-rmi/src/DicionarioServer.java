import java.rmi.Naming;

public class DicionarioServer {

	public DicionarioServer(String enderecoServidor) {
		try {
			Dicionario d = new DicionarioServant(); //instancia o objeto remoto
			Naming.rebind("rmi://" + enderecoServidor + "/DicionarioService", d); //registra o objeto remoto
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		String enderecoServidor = "localhost";

		if(args != null && args.length > 0){
			enderecoServidor = args[0];
		}
		new DicionarioServer(enderecoServidor); //inicia o servidor
		System.out.println("Servidor do Dicionario em execução no endereço: rmi://" + enderecoServidor + "/DicionarioService");
	}
}