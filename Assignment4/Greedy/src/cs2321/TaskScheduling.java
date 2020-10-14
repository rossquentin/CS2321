package cs2321;

public class TaskScheduling {
	/**
	 * Goal: Perform all the tasks using a minimum number of machines. 
	 * 
	 *       
	 * @param tasks tasks[i][0] is start time for task i
	 *              tasks[i][1] is end time for task i
	 * @return The minimum number or machines
	 */
	@TimeComplexity("O(n lg n)")
   public static int NumOfMachines(int[][] tasks) {

       HeapPQ<Integer, Integer> taskPQ = new HeapPQ<>();
       HeapPQ<Integer, Integer> machinePQ = new HeapPQ<>();

       // Inserts all tasks into the taskPQ, with key being the start time and value being the end time of the task.
       for(int[] task : tasks) {
           taskPQ.insert(task[0], task[1]);
       }

       while (!taskPQ.isEmpty()) {

           // If either there are no machines created or the machine is busy, allocate a new machine and
           // set it's available time to the end of the assigned task.
           if(machinePQ.isEmpty() || machinePQ.min().getKey() > taskPQ.min().getKey()) {
               machinePQ.insert(taskPQ.removeMin().getValue(), null);
           }

           // Otherwise there is a machine available. Assign the machine this task and
           // set it's available time to the end of the assigned task.
           else {
               machinePQ.replaceKey(machinePQ.min(), taskPQ.removeMin().getValue());
           }
       }

       // Return the number of machines used.
       return machinePQ.size();
   }
}
