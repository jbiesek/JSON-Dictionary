import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

public class Json {
    static private final ObjectMapper mapper = new ObjectMapper();

    public static void read(File file) throws IOException {
        Language language = mapper.readValue(file, Language.class);
        LanguageMap.languageMapAdd(language);
    }

    public static void findJsonFiles () throws IOException {
        File f = new File(System.getProperty("user.dir"));
        File[] matchingFiles = f.listFiles((dir, name) -> name.endsWith(".json"));
        assert matchingFiles != null;
        for (File i : matchingFiles){
            read(i);
        }
    }

    public static void addLanguage() throws IOException {
        String langName;
        Set<String> wordsSet = new HashSet<>();
        String word;
        int userChoice = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę języka, który chcesz dodać: ");
        langName = scanner.nextLine();
        String[] languageNames = LanguageMap.languagesNames();
        for (String languageName : languageNames){
            if (languageName.equals(langName)){
                System.out.println("Istnieje już taki język.\n1 - nadpisz język\n2 - dopisz słowa do istniejącego języka");
                userChoice = scanner.nextInt();
            }
        }
        if (userChoice == 1) {
            System.out.println("Podaj słowa (aby zakończyć wczytywanie wpisz \"koniec\"):");
            while (true) {
                word = scanner.nextLine();
                if (word.equals("koniec")) break;
                if (!word.equals("")) {
                    wordsSet.add(word);
                }
            }
            String[] wordsTab = wordsSet.toArray(String[]::new);
            Language language = new Language(wordsTab, langName);
            LanguageMap.languageMapAdd(language);
            String fileName = langName + ".json";
            mapper.writeValue(new File(fileName), language);
            Stats.statAdd(1, langName);
        } else if (userChoice == 2) {
            System.out.println("Podaj słowa (aby zakończyć wczytywanie wpisz \"koniec\"):");
            while (true) {
                word = scanner.nextLine();
                if (word.equals("koniec")) break;
                if (!word.equals("")) {
                    wordsSet.add(word);
                }
            }
            String[] usedWords = LanguageMap.getWordList(langName);
            wordsSet.addAll(Arrays.asList(usedWords));
            String[] words = wordsSet.toArray(String[]::new);
            Language language = new Language(words, langName);
            LanguageMap.languageMapAdd(language);
            String fileName = langName + ".json";
            mapper.writeValue(new File(fileName), language);
            Stats.statAdd(2, langName);
        }
    }

    public static void deleteLanguage() throws IOException {
        String[] languageNames = LanguageMap.languagesNames();
        System.out.println("Który język chcesz usunąć?");
        int i = 1;
        for (String name : languageNames){
            System.out.println(i + ". " + name);
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        String langToDel = languageNames[userChoice-1];
        LanguageMap.deleteLanguage(langToDel);
        String fileName = langToDel + ".json";
        File f= new File(fileName);
        if(f.delete())
        {
            System.out.println("Pomyślnie usunięto.");
            Stats.statAdd(3, langToDel);
        }
    }
}
