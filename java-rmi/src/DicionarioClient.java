import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class DicionarioClient {

	public static void main(String[] args) {
		String servidor = "rmi://localhost/";
		String nome = "DicionarioService";
		try {
			Dicionario d = (Dicionario) Naming.lookup(servidor + nome);
			System.out.println("Objeto remoto \'"+ nome + "\' encontrado no servidor.");

			String palavra, significado;
            System.out.println("Digite a palavra: ");
            palavra = System.console().readLine();
            System.out.println("Digite o significado: ");
            significado = System.console().readLine();

			// System.out.println(d.consultar(palavra));
			// System.out.println(d.adicionar(palavra, significado));
			// System.out.println(d.remover(palavra));
		

		} catch (MalformedURLException e) {
			System.out.println("URL \'" + servidor + nome + "\' mal formatada.");
		} catch (RemoteException e) {
			System.out.println("Erro na invocacao remota.");
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("Objeto remoto \'" + nome + "\' nao esta disponivel.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
