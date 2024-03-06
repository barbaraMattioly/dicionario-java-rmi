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

// Implementação do objeto remoto
public class DicionarioServant extends java.rmi.server.UnicastRemoteObject implements Dicionario {

    private static final long serialVersionUID = 1L; 

    // Construtor
    public DicionarioServant() throws java.rmi.RemoteException {
        super();
    }

    /**
     * Método para consultar palavra no dicionário
     * @param palavra
     * @return significado da palavra ou mensagem de erro
     */
    public String consultar(String palavra) throws java.rmi.RemoteException {
        String caminhoArquivo = "../src/arquivos/Dicionario.csv";
        try {
            List<String> palavras = Files.readAllLines(Paths.get(caminhoArquivo));
            for (String linha : palavras) {
                String[] linhaAtual = linha.split(",");

                if (linhaAtual.length == 2 && linhaAtual[0].equals(palavra)) {
                    return linhaAtual[1];
                }
            }
            return "Palavra não encontrada.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao ler o arquivo: " + e.getMessage();
        }
    }

    /**
     * Método para adicionar uma palavra ao dicionário
     * @param palavra
     * @param significado
     * @return mensagem de sucesso ou duplicada
     */
    public String adicionar(String palavra, String significado) throws java.rmi.RemoteException {
        String caminhoArquivo = "../src/arquivos/Dicionario.csv";
        try {
            File file = new File(caminhoArquivo);

            if (palavraJaExiste(palavra, caminhoArquivo)) {
                return "Duplicado";
            }
            
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

   
    
    /**
     * Método para remover uma palavra do dicionário
     * @param palavra
     * @return mensagem de sucesso ou não encontrada
     */
    public String remover(String palavra) throws java.rmi.RemoteException {
        String caminhoArquivo = "../src/arquivos/Dicionario.csv";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
                BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo + ".tmp"))) {
    
            String linha;
            Boolean palavraEncontrada = false;

            while ((linha = reader.readLine()) != null) {
                // Verifica se a linha está vazia ou contém apenas espaços em branco
                if (linha.trim().isEmpty()) {
                    continue; // Pula linhas vazias ou com apenas espaços
                }
    
                String[] linhaAtual = linha.split(",");
    
                if (linhaAtual.length == 2 && linhaAtual[0].equals(palavra)) {
                    palavraEncontrada = true;
                    continue; // Pula a linha que contém a palavra a ser removida
                }
    
                writer.write(linha + "\n"); // Escreve a linha no arquivo temporário
            }

            if (!palavraEncontrada) {
                return "Palavra não encontrada.";
            }
        } catch (IOException e) {
            System.out.println("Erro ao remover palavra do arquivo: " + e.getMessage());
            return "Erro ao acessar o arquivo.";
        }
    
        try {
            Files.move(Paths.get(caminhoArquivo + ".tmp"), Paths.get(caminhoArquivo),
                    StandardCopyOption.REPLACE_EXISTING);
            return "Sucesso.";
        } catch (IOException e) {
            System.out.println("Erro ao renomear arquivo temporário: " + e.getMessage());
            return "Erro ao atualizar o dicionário.";
        }
    }


    /**
     * Método para verificar se a palavra já existe no dicionário, antes te inserí-la.
     * @param palavra
     * @param caminhoArquivo
     * @return
     */
    private boolean palavraJaExiste(String palavra, String caminhoArquivo) {
        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
            for (String linha : linhas) {
                String[] partes = linha.split(",");
                if (partes.length >= 1 && partes[0].equals(palavra)) {
                    return true; // Palavra encontrada
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao verificar duplicata: " + e.getMessage());
        }
        return false; // Palavra não encontrada
    }
   

}