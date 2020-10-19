package cs2321;

import net.datastructures.Entry;

/**
 * @author: Quentin Ross
 *
 */
public class FractionalKnapsack {
	/**
	 * Goal: Choose items with maximum total benefit but with weight at most W.
	 *       You are allowed to take fractional amounts from items.
	 *       
	 * @param items items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
	 * @param knapsackWeight
	 * @return The maximum total benefit. Please use double type operation. For example 5/2 = 2.5
	 * 		 
	 */
	@TimeComplexity("O(n lg n)")
	public static double MaximumValue(int[][] items, int knapsackWeight) {
        int totalWeight = 0;
        double benefit = 0;
        HeapPQ<Integer, Integer> pq = new HeapPQ<>(new MaximumComparator<>());

        // Insert all items into the PQ, with the key being b/w and the value being b.
        for (int[] item : items) {
            pq.insert(item[1]/item[0], item[0]);
        }

        while (!pq.isEmpty()) {
            // Get the item with the highest value.
            Entry<Integer,Integer> current = pq.removeMin();
            int currentValue = current.getKey();
            int currentWeight = current.getValue();

            // If adding the item's weight would overload the knapsack,
            // Add the maximum amount you can before overloading the knapsack.
            if (totalWeight + currentWeight > knapsackWeight) {
                benefit += currentValue * (knapsackWeight - totalWeight);
                break;
            }

            // Otherwise, add the value and weight of the item to the knapsack.
            benefit += currentValue * currentWeight;
            totalWeight += currentWeight;
        }

        // Return the total value of the knapsack.
		return benefit;
	}
}
