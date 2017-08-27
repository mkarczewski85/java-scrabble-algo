import java.util.ArrayList;
import java.util.List;

public class PowerSet {

    public static List<String> powerSet(String s) {
        // the number of subsets is 2^n
        long numSubsets = 1L << s.length();
        return powerSet(s, numSubsets - 1);
    }

    private static List<String> powerSet(String s, long active) {
        if (active < 0) {
            // Recursion base case
            // All 2^n subsets were visited, stop here and return a new list
            return new ArrayList<>();
        }

        StringBuilder subset = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            // For each bit
            if (isSet(active, i)) {
                // If the bit is set, add the correspondent char to this subset
                subset.append(s.charAt(i));
            }
        }
        // Make the recursive call, decrementing active to the next state,
        // and get the returning list
        List<String> subsets = powerSet(s, active - 1);
        // Add this subset to the list of subsets
        subsets.add(subset.toString());
        return subsets;
    }

    private static boolean isSet(long bits, int i) {
        // return true if the ith bit is set
        return (bits & (1L << i)) != 0;
    }

}
