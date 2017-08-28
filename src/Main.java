import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        char[] a = {'i', 'm', 'p', 'r', 'a', 's', 'ł'};

        Set<String> allPossibleWords = findAllPossibleWords(a);
        if (!allPossibleWords.isEmpty()) {
            System.out.println("Znaleziono " + allPossibleWords.size() + " słów: \n");
            printFoundWords(allPossibleWords);
        } else {
            System.out.println("Nie znaleziono żadnego słowa");
        }

    }

    public static void printFoundWords(Set<String> foundWords) {
        int score = 0;
        int highestScore = 0;
        String wordWithHighestScore = null;

        for (String s : foundWords) {
            score = WordPoints.calcFinalScore(s);
            System.out.println(s + " : " + score + " pkt.");
            if (score > highestScore) {
                highestScore = score;
                wordWithHighestScore = s;
            }
        }

        System.out.println("\nNajwyżej punktowane słowo: " + wordWithHighestScore + " (" + highestScore + " pkt.)");
    }

    public static Set<String> findAllPossibleWords(char[] a) {
        Map<String, String> newMap = getListWordsFromFile("slowa.txt");
        Set<String> foundWords = new TreeSet<>();

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

        return foundWords;
    }

    private static Map<String, String> getListWordsFromFile(String filepath) {

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
