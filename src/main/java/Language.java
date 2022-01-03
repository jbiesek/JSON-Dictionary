import java.util.ArrayList;
import java.util.List;

public class Language {
    String[] words;
    String language;

    public Language() {}


    public Language(String[] words, String language) {
        this.words = words;
        this.language = language;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
