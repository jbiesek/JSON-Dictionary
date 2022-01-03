import org.junit.Assert;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LanguageMapTest {

    @org.junit.jupiter.api.Test
    void findWordTest1() {
        //given
        String word = "a";
        HashMap<String, String[]> langMap = new HashMap<String, String[]>();
        langMap.put("English", new String[]{"a","b","c","d","e","f","g","h"});
        langMap.put("German", new String[]{"a","b","c","d","e","f"});

        //when
        String[] result = LanguageMap.findWord(word, langMap,2);

        //then
        assertEquals("English", result[0]);
        assertEquals("German", result[1]);
    }

    @org.junit.jupiter.api.Test
    void findWordTest2() {
        //given
        String word = "g";
        HashMap<String, String[]> langMap = new HashMap<String, String[]>();
        langMap.put("English", new String[]{"a","b","c","d","e","f","g","h"});
        langMap.put("German", new String[]{"a","b","c","d","e","f"});

        //when
        String[] result = LanguageMap.findWord(word, langMap,2);

        //then
        assertEquals("English", result[0]);
        assertNull(result[1]);
    }

    @org.junit.jupiter.api.Test
    void findWordTest3() {
        //given
        String word = "x";
        HashMap<String, String[]> langMap = new HashMap<String, String[]>();
        langMap.put("English", new String[]{"a","b","c","d","e","f","g","h"});
        langMap.put("German", new String[]{"a","b","c","d","e","f"});

        //when
        String[] result = LanguageMap.findWord(word, langMap,2);

        //then
        assertNull(result[0]);
        assertNull(result[1]);
    }

}