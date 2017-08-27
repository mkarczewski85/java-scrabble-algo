import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<String, String> newMap = getListWordsFromFile("slowa.txt");
        Set<String> foundWords = new TreeSet<>();

        System.out.println("Baza załadowana!");
        System.out.println();
        char[] a = {'k', 'm', 'ś', 'a', 'a', 's', 'a'};

        List<String> combinations = PowerSet.powerSet(new String(a));
        char[] temp;
        for (int i = 0; i < combinations.size(); i++) {
            temp = combinations.get(i).toCharArray();
            Arrays.sort(temp);
            combinations.set(i, new String(temp));
        }

        for (int i = 0; i < combinations.size(); i++) {
            for (String word : newMap.keySet()) {
                if (newMap.get(word).equals(combinations.get(i))) {
                    foundWords.add(word);
                }
            }
        }

        for (String s : foundWords) {
            System.out.println(s);
        }
    }


    public static Map<String, String> getListWordsFromFile(String filepath) {

        Map<String, String> wordsMap = new HashMap<>();

        try (BufferedReader bReader = Files.newBufferedReader(Paths.get(filepath))) {

            String wordLine = null;
            char[] orderedChars = null;
            while ((wordLine = bReader.readLine()) != null) {
                orderedChars = wordLine.toCharArray();
                Arrays.sort(orderedChars);
                wordsMap.put(wordLine, new String(orderedChars));
            }

        } catch (Exception e) {
            System.out.println("Bład odczytu pliku");
            e.printStackTrace();
        }
        return wordsMap;
    }
}
