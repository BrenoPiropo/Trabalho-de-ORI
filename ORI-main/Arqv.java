import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe para ler os Arquivos
public class Arqv {

    //Faz a leitura do arquivo e retorna a quantidade de linhas que ele tem
    public static int readFile(List<String> str, String fileName) throws IOException{
        Scanner scn = new Scanner(new FileReader(fileName));
        String string;
        int i = 0;

        while(scn.hasNext()){
            string = Remove.removeChar(scn.next());
            str.add(string);
            i++;
        }

        return i; //Retorna o numero de palavras do arquivo
    }

    //Faz a leitura do arquivo e guarda na lista de listas
    public static List<List<String>> readArqv(List<List<String>> Listlist,List<String> list, int count1, int[] count2) throws IOException{
        for(int i = 0; i < count1; i++){
            List<String> s = new ArrayList<String>();
            count2[i] = Arqv.readFile(s, list.get(i)); //Aqui o algoritmo lê um arquivo e salva o numero de palavras dele no vetor count

            Listlist.add(s); //Adicio na lista de listas
        }

        return Listlist;
    }

}
