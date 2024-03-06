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

		String servidor = "rmi://localhost/"; //servidor
		String nome = "DicionarioService"; //nome do objeto remoto
		try {
			d = (Dicionario) Naming.lookup(servidor + nome); //busca o objeto remoto no servidor
			System.out.println("Objeto remoto \'" + nome + "\' encontrado no servidor."); //mensagem de sucesso

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

	//Método para exibir menu de opções
	private static void exibirMenu() {

		String nomeArquivo = "./java-rmi/src/arquivos/Menu.txt";

		try {
			List<String> linhas = Files.readAllLines(Paths.get(nomeArquivo)); //lê as linhas do arquivo criado para o menu

			for (String linha : linhas) {
				System.out.println(linha);
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}
	}

	//Construção do menu
	private static void opcoesMenu() throws IOException {
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

	//Método que consulta o significado de uma palavra no dicionário
	private static void consultarSignificado() throws RemoteException {
		try{
			String palavra;

			System.out.print("Digite a palavra: ");
			palavra = System.console().readLine();

			System.out.println(d.consultar(palavra));
		} catch (RemoteException e) {
			System.out.println("Erro na invocacao remota.");
			e.printStackTrace();
		}
	}

	//Método que adiciona uma palavra ao dicionário
	private static void adicionarPalavra() throws RemoteException {
		try{
			String palavra, significado;

			System.out.print("Digite a palavra: ");
			palavra = System.console().readLine();

			System.out.print("Digite o significado: ");
			significado = System.console().readLine();

			System.out.println(d.adicionar(palavra, significado));
		}catch (RemoteException e) {
			System.out.println("Erro na invocacao remota.");
			e.printStackTrace();
		}
	}

	//Método que remove uma palavra do dicionário
	private static void removerPalavra() throws IOException {
		try{
			String palavra;

			System.out.print("Digite a palavra: ");
			palavra = System.console().readLine();

			System.out.println(d.remover(palavra));
		}catch (RemoteException e) {
			System.out.println("Erro na invocacao remota.");
			e.printStackTrace();
		}
	}

	//Método para limpar a tela ao rodar o programa
	private static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	//Método para pausar a execução do programa
	private static void pausa() {
		System.out.println("Enter para continuar.");
		System.console().readLine();
	}
}