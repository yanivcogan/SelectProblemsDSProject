import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SelectProblems
{

  enum PivotMethod{
    RANDOM,
    MED_OF_MEDS
  }

  public Pair<Integer, Integer> selectRandQuickSort(int [] array, int k)
  {
    ComparisonCounter c = new ComparisonCounter();
    quickSortRec(array, 0, array.length, c);
    return new Pair<Integer, Integer>(array[k - 1], c.getCount()); // to be replaced by student code. (The k'th element,#of comparsion)
  }
  private int[] quickSortRec(int[] array, int start, int end, ComparisonCounter c){
    if (end - start <= 0)
      return array;
    //choose a random pivot element
    int pivot = array[(int)(Math.random() * (end - start) + start)];
    Partition p = new Partition(array, pivot, start, end, c);
    //sub-partition both segments of the partition
    quickSortRec(p.array, 0, p.lessThanSeparator, c);
    quickSortRec(p.array, p.greaterThanSeparator, p.array.length, c);
    //apply partition in-place to the input array
    for(int i = start; i < end; i++){
      array[i] = p.array[i - start];
    }
    return array;
  }

  //swaps the indexes of two members in array
  private static void swap(int [] array, int ind1, int ind2)
  {
   int temp = array[ind1];
    array[ind1] = array[ind2];
    array[ind2] = temp;
  }

  public Pair<Integer, Integer> selectInsertionSort(int[] array, int k) {
    return selectInsertionSort(array, k, 0, array.length, new ComparisonCounter());
  }

  public Pair<Integer, Integer> selectInsertionSort(int [] array, int k, int start, int end, ComparisonCounter c)
  {
    for(int i = start + 1; i < end; ++i) {
      int j = i;
      while (j > start && c.less(array[j], array[j - 1])) {
        swap(array, j, j - 1);
        j--;
      }
    }
    return new Pair<Integer, Integer>(array[start + k - 1], c.getCount());
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
    ComparisonCounter c = new ComparisonCounter();
    Integer selected = quickSelectRec(array, k, 0, array.length, c, PivotMethod.RANDOM);
    return new Pair<Integer, Integer>(selected, c.getCount()); // to be replaced by student code. (The k'th element,#of comparsion)
  }

  private int quickSelectRec(int[] array, int k, int start, int end, ComparisonCounter c, PivotMethod method){
    if (end - start <= 0)
      return array[k - 1];
    //choose a random pivot element
    int pivot = (method == PivotMethod.MED_OF_MEDS)?
            medOfMed(array, start, end, c) : array[(int)(Math.random() * (end - start) + start)];
    Partition p = new Partition(array, pivot, start, end, c);
    if(p.lessThanSeparator >= k)
      return quickSelectRec(p.array, k, 0, p.lessThanSeparator, c, method);
    if(p.greaterThanSeparator < k)
      return quickSelectRec(p.array, k - p.greaterThanSeparator, p.greaterThanSeparator, p.array.length, c, method);
    return pivot;
  }
  public int medOfMed(int [] array, int start, int end, ComparisonCounter c)
  {
    if(end - start <= 5)
      return mid5(array, start, end, c);;
    int i;
    int selected = 0;
    int nMedians = (end - start) / 5;
    nMedians += (end - start) % 5 == 0 ? 0 : 1;
    int[] mids = new int[nMedians];

    for(i = start; i < end - 5; i = i + 5)
    {
      mids[selected] = selectInsertionSort(array, 3, i, i + 4, c).getKey();
      selected++;
    }
    mids[selected] = mid5(array, i, end, c);
    return  quickSelectRec(mids, (nMedians + 1)/2 ,0,nMedians, c, PivotMethod.MED_OF_MEDS);
  }
//change to private
  public int mid5(int[] array, int start, int end, ComparisonCounter c) {
    if(c == null)
      c = new ComparisonCounter(); //for debug
    int size = end - start;
    if(size == 1) {
      return array[start];
    }
    selectInsertionSort(array, 1, start, end, c);
    if(size % 2 == 1)
      return array[start + (size / 2)];
    return size == 2? (array[0] + array[1])/2 : (array[2] + array[3])/2; // in this case array size is 2 or 4
  }

  public Pair<Integer, Integer> medOfMedQuickSelect(int [] array, int k)
  {
    ComparisonCounter c = new ComparisonCounter();
    Integer selected = quickSelectRec(array, k, 0, array.length, c, PivotMethod.MED_OF_MEDS);
    return new Pair<Integer, Integer>(selected, c.getCount()); // to be replaced by student code. (The k'th element,#of comparsion)
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
      this.greaterThanSeparator = partition.length - indexFromEnd;
    }
  }
  private static class ComparisonCounter{
    private int counter;
    public int comp(Integer a, Integer b){
      counter ++;
      //System.out.println("comparing " + a + " and " + b);
      return Integer.compare(a, b);
    }

    public boolean less(Integer a, Integer b){
      counter ++;
      return a < b;
    }
    public int getCount(){
      return counter;
    }
  }
}