import java.util.List;

//Classe para remover caracteres
public class Remove {
    //Remove caracteres indesejados
    public static String removeChar(String S){
        S = removeSpace(S);
        S = removeEMark(S);
        S = removeQMark(S);

        return S;
    }

    //Remove ponto e vírgula, sem interferir nos arquivos base
    public static List<List<String>> internalRemove(List<List<String>> List, int count1, int[] count2){
        for(int i = 0; i < count1; i++){
            for(int j = 0; j < count2[i]; j++){
                List.get(i).set(j, listRemove(List.get(i).get(j))); 
            }
        }

        return List;
    } 
    
    //Remove palavras desconsideradas
    public static List<List<String>> WordRemove(List<List<String>> List, int count1, int[] count2, List<String> list2, int count3){
        for(int i = 0; i < count1; i++){
            for(int j = 0; j < count2[i]; j++){

                //Compara cada palavra com a lista inteira de palavras desconsideradas (vê se tem como melhorar se n, esquece)
                for(int k = 0; k < count3; k++){
                    if(List.get(i).get(j).equals(list2.get(k))){
                        List.get(i).remove(list2.get(k));
                        count2[i] = count2[i] - 1; //Atualiza o número de palavra do arquivo(pois algumas palavras foram tiradas)
                    }
                }

            }
        }

        return List;
    }

    //Remove caracteres indesejados
    private static String listRemove(String S){
        S = removeDot(S);
        S = removeComma(S);

        return S;
    }

    //Remove os espaços da String
    private static String removeSpace(String S){
        return S.replace(" ", "");
    }

    //Remove os pontos da String
    private static String removeDot(String S){
        return S.replace(".", "");
    }

    //Remove as virgulas da String
    private static String removeComma(String S){
        return S.replace(",", "");
    }

    //Remove as exclamações da String
    private static String removeEMark(String S){
        return S.replace("!", "");
    }

     //Remove as interrogações da String
     private static String removeQMark(String S){
        return S.replace("?", "");
    }
}
