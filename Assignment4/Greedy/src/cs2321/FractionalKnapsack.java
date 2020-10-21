package cs2321;

import java.util.Comparator;

/**
 * @author: Quentin Ross
 */
public class FractionalKnapsack {
    /**
     * Goal: Choose items with maximum total benefit but with weight at most W.
     * You are allowed to take fractional amounts from items.
     *
     * @param items          items[i][0] is weight for item i
     *                       items[i][1] is benefit for item i
     * @param knapsackWeight maximum capacity of the knapsack
     * @return The maximum total benefit. Please use double type operation. For example 5/2 = 2.5
     */
    @TimeComplexity("O(n lg n)")
    public static double MaximumValue(int[][] items, int knapsackWeight) {
        int totalWeight = 0;
        double benefit = 0;

        Comparator<Double> comp = (a, b) -> -1 * ((Comparable<Double>) a).compareTo(b);
        HeapPQ<Double, Integer> pq = new HeapPQ<>(comp);

        // Insert all items into the PQ, with the key being b/w and the value being b.
        for (int[] item : items) {
            pq.insert((double) item[1] / (double) item[0], item[0]);
        }

        while (!pq.isEmpty() && totalWeight < knapsackWeight) {
            // Get the item with the highest value.
            double currentValue = pq.min().getKey();
            int currentWeight = pq.min().getValue();

            // Add the maximum amount of benefit and weight allowed to the knapsack.
            benefit += currentValue * Math.min(currentWeight, knapsackWeight - totalWeight);
            totalWeight += currentWeight;

            // Finished with all calculations. Remove maximum element.
            pq.removeMin();
        }

        // Return the total value of the knapsack.
        return benefit;
    }
}
