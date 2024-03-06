import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.NotBoundException;

public class DicionarioClient {
	private static Dicionario d;
	public static void main(String[] args) {
		String servidor = "rmi://localhost/";
		String nome = "DicionarioService";
		try {
			d = (Dicionario) Naming.lookup(servidor + nome);
			System.out.println("Objeto remoto \'" + nome + "\' encontrado no servidor.");

			opcoesMenu();

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

	private static void exibirMenu() {
		String nomeArquivo = "./java-rmi/src/arquivos/Menu.txt";

		try {
			List<String> linhas = Files.readAllLines(Paths.get(nomeArquivo));

			for (String linha : linhas) {
				System.out.println(linha);
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}
	}

	private static void opcoesMenu() throws RemoteException {
		int opcao = 0;
		do {
			exibirMenu();
			System.out.print("Selecione a opção: ");
			try {
				opcao = Integer.parseInt(System.console().readLine());
			} catch (NumberFormatException e) {
				System.out.println("Opção inválida.");
			}

			limparTela();
			switch (opcao) {
				case 1:
					consultarSignificado();
					break;
				case 2:
					adicionarPalavra();
					break;
				case 3:
					removerPalavra();
					break;
				default:
					break;
			}
			pausa();
			limparTela();
		} while (opcao != 4);
	}

	private static void consultarSignificado() throws RemoteException {
		String palavra;

		System.out.print("Digite a palavra: ");
		palavra = System.console().readLine();

		System.out.println(d.consultar(palavra));
	}

	private static void adicionarPalavra() throws RemoteException {
		String palavra, significado;

		System.out.print("Digite a palavra: ");
		palavra = System.console().readLine();

		System.out.print("Digite o significado: ");
		significado = System.console().readLine();

		System.out.println(d.adicionar(palavra, significado));
	}

	private static void removerPalavra() throws RemoteException {
		String palavra;

		System.out.print("Digite a palavra: ");
		palavra = System.console().readLine();

		System.out.println(d.remover(palavra));
	}

	private static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private static void pausa() {
		System.out.println("Enter para continuar.");
		System.console().readLine();
	}
}
