import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.Arrays;


public class SelectProblems
{

  public Pair<Integer, Integer> selectRandQuickSort(int [] array, int k)
  {
    ComparisonCounter c = new ComparisonCounter();
    quickSortRec(array, 0, array.length, c);
    System.out.println(Arrays.toString(array));
    return new Pair<Integer, Integer>(array[k], c.getCount()); // to be replaced by student code. (The k'th element,#of comparsion)
  }
  private int[] quickSortRec(int[] array, int start, int end, ComparisonCounter c){
    if (end - start <= 0)
      return array;
    //choose a random pivot element
    int pivot = array[(int)(Math.random() * (end - start) + start)];
    Partition p = new Partition(array, pivot, start, end, c);
    //sub-partition both segments of the partition
    quickSortRec(p.array, 0, p.lessThanSeparator, c);
    quickSortRec(p.array, p.array.length - p.greaterThanSeparator, p.array.length, c);
    //apply partition in-place to the input array
    for(int i = start; i < end; i++){
      array[i] = p.array[i - start];
    }
    return array;
  }
  public Pair<Integer, Integer> selectInsertionSort(int [] array, int k)
  {
    return new Pair<Integer, Integer>(-1,-1); // to be replaced by student code. (The k'th element,#of comparsion)
  }
  public Pair<Integer, Integer> selectHeap(int [] array, int k)
  {
    return new Pair<Integer, Integer>(-1,-1); // to be replaced by student code. (The k'th element,#of comparsion)
  }
  public Pair<Integer, Integer> selectDoubleHeap(int [] array, int k)
  {
    return new Pair<Integer, Integer>(-1,-1); // to be replaced by student code. (The k'th element,#of comparsion)
  }
  public Pair<Integer, Integer> randQuickSelect(int [] array, int k)
  {
    return new Pair<Integer, Integer>(-1,-1); // to be replaced by student code. (The k'th element,#of comparsion)
  }
  public Pair<Integer, Integer> medOfMedQuickSelect(int [] array, int k)
  {
    return new Pair<Integer, Integer>(-1,-1); // to be replaced by student code. (The k'th element,#of comparsion)
  }
  private static class Partition{
    //the array containing the partition
    private int[] array;
    //marks the end of the segment of the partition containing items less than the pivot
    private int lessThanSeparator;
    //marks the beginning of the segment of the partition containing items less than the pivot
    private int greaterThanSeparator;
    public Partition(int[] array, int pivot, int start, int end, ComparisonCounter c){
      int[] partition = new int[end - start];
      //We are going to generate a partition by inserting elements to both the start and the end of an array
      //These variables keep track of where the next element should be inserted
      int indexFromStart = 0;
      int indexFromEnd = 0;
      //sort out elements bigger/smaller than the pivot element into different sides
      for(int i = start; i < end; i++){
        if(c.comp(pivot, array[i]) > 0){
          partition[indexFromStart] = array[i];
          indexFromStart++;
        }
        if(c.comp(pivot, array[i]) < 0){
          partition[partition.length - indexFromEnd - 1] = array[i];
          indexFromEnd++;
        }
      }
      //all remaining elements are equal to the pivot element
      for(int i = indexFromStart; i < (partition.length - indexFromEnd); i++){
        partition[i] = pivot;
      }
      this.array = partition;
      this.lessThanSeparator = indexFromStart;
      this.greaterThanSeparator = indexFromEnd;
    }
  }
  private static class ComparisonCounter{
    private int counter;
    public int comp(Integer a, Integer b){
      counter ++;
      //System.out.println("comparing " + a + " and " + b);
      return Integer.compare(a, b);
    }
    public int getCount(){
      return counter;
    }
  }
}