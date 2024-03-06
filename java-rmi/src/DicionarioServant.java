import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class DicionarioServant extends java.rmi.server.UnicastRemoteObject implements Dicionario {

    private static final long serialVersionUID = 1L;

    public DicionarioServant() throws java.rmi.RemoteException {
        super();
    }

    public String consultar(String palavra) throws java.rmi.RemoteException {
        String caminhoArquivo = "../src/arquivos/Dicionario.csv";
        try {
            List<String> palavras = Files.readAllLines(Paths.get(caminhoArquivo));
            for (String linha : palavras) {
                String[] linhaAtual = linha.split(",");

                if (linhaAtual.length == 2 && linhaAtual[0].equals(palavra)) {
                    return palavra + ": " + linhaAtual[1];
                }
            }
            return "Palavra não encontrada.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao ler o arquivo: " + e.getMessage();
        }
    }

    public String adicionar(String palavra, String significado) throws java.rmi.RemoteException {
        String caminhoArquivo = "../src/arquivos/Dicionario.csv";
        try {
            File file = new File(caminhoArquivo);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            if (file.length() == 0) {
                writer.write("Palavra,Significado\n");
            }

            writer.write(String.format("%s,%s\n", palavra, significado));
            
            writer.close();
            
            return "Sucesso";
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo CSV: " + e.getMessage()); //tratar exceção no client e nao aqui
        }
        return "";
    }

    public String remover(String palavra) throws java.rmi.RemoteException {
        String caminhoArquivo = "../src/arquivos/Dicionario.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
                BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo + ".tmp"))) {

            String linha;
            while ((reader.readLine()) != null) {
                linha = reader.readLine();

                String[] linhaAtual = linha.split(",");

                if (linhaAtual.length == 2 && linhaAtual[0].equals(palavra)) {
                    continue;
                }

                writer.write(linha + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao remover palavra do arquivo: " + e.getMessage());
        }

        try {
            Files.move(Paths.get(caminhoArquivo + ".tmp"), Paths.get(caminhoArquivo),
                    StandardCopyOption.REPLACE_EXISTING);
            return "Sucesso";
        } catch (IOException e) {
            System.out.println("Erro ao renomear arquivo temporário: " + e.getMessage());
        }
        return ""; //ajustar
    }
}