package hw3.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* My Code:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        double N = (double) oomages.size();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Oomage oo : oomages) {
            int bucketNum = (oo.hashCode() & 0x7FFFFFFF) % M;
            map.put(bucketNum, map.getOrDefault(bucketNum, 0) + 1);
        }
        boolean res = true;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > N / 2.5 || entry.getValue() < N / 50) {
                res = false;
                break;
            }
        }
        return res;
    }
}
