import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class LanguageMap {

    static HashMap<String, String[]> languageMap = new HashMap<String, String[]>();

    public static HashMap<String, String[]> getLanguageMap() {
        return languageMap;
    }

    public static void languageMapAdd(Language lang) {
        languageMap.put(lang.language.toString(), lang.words);
    }
    public static void printWords() {
        int k =0;
        for (String[] i : languageMap.values()) {
            System.out.println(languageMap.keySet().toArray()[k] + ":");
            List<String> wordList = new ArrayList<String>(Arrays.asList(i));
            Stream<String> stream = wordList.stream();
            stream.forEach(System.out::println);
            k++;
            System.out.println("");
        }
    }

    public static void printLanguages(){
        Stream<String> stream = languageMap.keySet().stream();
        stream.forEach(System.out::println);
    }

    public static String[] getWordList(String lang){
        String[] words = languageMap.get(lang);
        return words;
    }
    
    public static int howManyLanguages() {
        int k = 0;
        for (String[] ignored : languageMap.values()) {
            k++;
        }
        return k;
    }

    public static String[] languagesNames () {
        String[] languageNames = new String[howManyLanguages()];
        for (int i = 0; i < howManyLanguages(); i ++) {
            languageNames[i] = languageMap.keySet().toArray()[i].toString();
        }
        return languageNames;
    }

    public static void findWordInputOutput() throws IOException {
        int langCount = howManyLanguages();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jakie słowo chcesz wyszukać?");
        String word = scanner.nextLine();
        FileWriter file = new FileWriter("tmp/stats.txt", true);
        BufferedWriter out = new BufferedWriter(file);
        String[] langNames = findWord(word, languageMap, langCount);
        Stats.statAdd(4, word);
        if (langNames[0] == null){
            System.out.println("Nie znaleziono słowa w bazie");
            out.write("Nie znaleziono slowa w bazie\n");
        } else {
            for(String langName : langNames){
                if(langName != null) {
                    System.out.println(langName + ": " + word);
                    out.write(langName + ": " + word + "\n");
                }
            }
        }
        out.close();
        file.close();
        Stats.pdfCreate();
    }

    public static String[] findWord(String word, HashMap<String, String[]> map, int langCount) {
        String[] langNames = new String[langCount];
        int k = 0;
        int l =0;
        for (String[] i : map.values()) {
            List<String> wordList = new ArrayList<>(Arrays.asList(i));
            Stream<String> stream = wordList.stream()
                            .filter(f -> f.equals(word));
            if(stream.findAny().isPresent()){
                langNames[l] = map.keySet().toArray()[k].toString();
                l++;
                k++;
            };
        }
        return langNames;
    }

    public static void deleteLanguage(String langName){
        languageMap.remove(langName);
    }

}
