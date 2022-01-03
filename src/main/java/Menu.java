import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {
    public static void menu() throws IOException {
        while (true) {
            System.out.println("1 - pokaż dostępne języki\n2 - pokaż słowa z każdego języka\n3 - dodaj nowy język\n4 - znajdź dane słowo\n5 - usuń wybrany język\n6 - wyjdź z programu");
            Scanner scanner = new Scanner(System.in);
            int userChoice = scanner.nextInt();
            if (userChoice == 1) {
                LanguageMap.printLanguages();
            } else if (userChoice == 2) {
                LanguageMap.printWords();
            } else if (userChoice == 3) {
                Json.addLanguage();
            } else if (userChoice == 4){
                LanguageMap.findWordInputOutput();
            } else if (userChoice == 5){
                Json.deleteLanguage();
            } else {
                exit(0);
            }
        }
    }
}
