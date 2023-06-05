import java.io.*;
import java.util.*;

public class Indice {
    public static void main(String[] args) {
        Map<String, Map<Integer, Integer>> contadorPalavras = new HashMap<>();
        // Coloque o caminho relativo da sua maquina para identificar os arquivos
        String nomeArquivoA = "C:\\Users\\breno\\Desktop\\ORI-main\\ORI-main\\a.txt";
        String nomeArquivoB = "C:\\Users\\breno\\Desktop\\ORI-main\\ORI-main\\b.txt";
        String nomeArquivoC = "C:\\Users\\breno\\Desktop\\ORI-main\\ORI-main\\c.txt";
        String arquivoDesconsideradas = "C:\\Users\\breno\\Desktop\\ORI-main\\ORI-main\\desconsideradas.txt";
        String arquivoIndice = "C:\\Users\\breno\\Desktop\\ORI-main\\ORI-main\\indice.txt";
        // Ler as palavras a serem desconsideradas do arquivo "desconsideradas.txt"
        Set<String> palavrasDesconsideradas = lerPalavrasDesconsideradas(arquivoDesconsideradas);

        // Remover os caracteres ',','!', , '?' dos arquivos a.txt, b.txt e c.txt
        removerCaracteresEspeciais(nomeArquivoA);
        removerCaracteresEspeciais(nomeArquivoB);
        removerCaracteresEspeciais(nomeArquivoC);

        // Ler os arquivos a.txt, b.txt e c.txt
        lerArquivo(nomeArquivoA, contadorPalavras, palavrasDesconsideradas, 1);
        lerArquivo(nomeArquivoB, contadorPalavras, palavrasDesconsideradas, 2);
        lerArquivo(nomeArquivoC, contadorPalavras, palavrasDesconsideradas, 3);

        // Escrever as palavras, suas contagens e os documentos no arquivo "indice.txt"
        try {
            PrintWriter writer = new PrintWriter(arquivoIndice);
            for (Map.Entry<String, Map<Integer, Integer>> entry : contadorPalavras.entrySet()) {
                String palavra = entry.getKey();
                Map<Integer, Integer> mapaDocumento = entry.getValue();
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<Integer, Integer> documentoEntry : mapaDocumento.entrySet()) {
                    Integer documento = documentoEntry.getKey();
                    Integer quantidade = documentoEntry.getValue();
                    sb.append(documento).append(",").append(quantidade).append(" ");
                }
                String registros = sb.toString().trim();

                // Remover os caracteres , ! ? .
                palavra = palavra.replaceAll("[,!?.]", "");

                writer.println(palavra + ": " + registros);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar o arquivo 'indice.txt'.");
            e.printStackTrace();
        }
    }

    public static Set<String> lerPalavrasDesconsideradas(String arquivo) {
        Set<String> palavrasDesconsideradas = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                palavrasDesconsideradas.add(linha.trim().toLowerCase());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo 'desconsideradas.txt'.");
            e.printStackTrace();
        }
        return palavrasDesconsideradas;
    }

    public static void removerCaracteresEspeciais(String arquivo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            StringBuilder conteudo = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.replaceAll("[,!?.]", "");
                conteudo.append(linha).append("\n");
            }
            reader.close();

            PrintWriter writer = new PrintWriter(arquivo);
            writer.print(conteudo.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + arquivo);
            e.printStackTrace();
        }
    }

    public static void lerArquivo(String arquivo, Map<String, Map<Integer, Integer>> contadorPalavras, Set<String> palavrasDesconsideradas, int documento) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] palavras = linha.trim().toLowerCase().split("\\s+");

                for (String palavra : palavras) {
                    if (!palavrasDesconsideradas.contains(palavra)) {
                        contadorPalavras.putIfAbsent(palavra, new HashMap<>());
                        Map<Integer, Integer> mapaDocumento = contadorPalavras.get(palavra);
                        mapaDocumento.put(documento, mapaDocumento.getOrDefault(documento, 0) + 1);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + arquivo);
            e.printStackTrace();
        }
    }
}
