package cs2321;

import net.datastructures.Entry;

/**
 * @author:
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
	public static double MaximumValue(int[][] items, int knapsackWeight) {
		 //TODO: Don't forget to modify the return value
        int totalWeight = 0;
        int benefit = 0;
        HeapPQ<Integer, Integer> pq = new HeapPQ<>(new MaximumComparator<>());

        for (int[] item : items) {
            pq.insert(item[1]/item[0], item[0]);
        }

        while (totalWeight < knapsackWeight) {
            Entry<Integer,Integer> current = pq.removeMin();
            int currentValue = current.getKey();
            int currentWeight = current.getValue();

            if (totalWeight + currentWeight > knapsackWeight) {
                int difference = totalWeight + currentValue - knapsackWeight;
                totalWeight += difference;
                benefit += currentValue * difference;
            }
            else {
                benefit += currentValue * currentWeight;
                totalWeight += currentWeight;
            }
        }
		return benefit;
	}
}
