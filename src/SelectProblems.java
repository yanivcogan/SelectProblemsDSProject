import javafx.util.Pair;
import java.util.Arrays;

public class SelectProblems
{

  enum PivotMethod{
    RANDOM,
    MED_OF_MEDS
  }

  /** uses Quicksort algorithm on input array and returns k-th smallest element.
   *  worst case O(n^2), average case is O(nlogn)
   */
  public Pair<Integer, Integer> selectRandQuickSort(int [] array, int k)
  {
    ComparisonCounter c = new ComparisonCounter();
    quickSortRec(array, 0, array.length, c);
    return new Pair<Integer, Integer>(array[k - 1], c.getCount());
  }

  /** uses Quicksort to sort range [start,end) of input array.
   * Comparison counter is an object used for keeping track of #comparisons done.
   *  worst case O(n^2), average case is O(nlogn)
   *  returns input array.
   */
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

  //swaps the indexes of two members in array. O(1)
  private static void swap(int [] array, int ind1, int ind2)
  {
    int temp = array[ind1];
    array[ind1] = array[ind2];
    array[ind2] = temp;
  }

  /** sorts input array and returns its k-th smallest element and #comparisons done. O(n^2) */
  public Pair<Integer, Integer> selectInsertionSort(int[] array, int k) {
    return selectInsertionSort(array, k, 0, array.length, new ComparisonCounter());
  }

  /** sorts input array in the range [start, end) and returns its k-th smallest element and #comparisons done. O(n^2)
   * ComparisonCounter is used for keeping track of #comparisons made */
  public Pair<Integer, Integer> selectInsertionSort(int [] array, int k, int start, int end, ComparisonCounter c)
  {
    for(int i = start + 1; i < end; ++i) {
      int j = i;
      while (j > start && c.less(array[j], array[j - 1])) { // less = true iff array[j]<array[j-1]
        swap(array, j, j - 1);
        j--;
      }
    }
    return new Pair<Integer, Integer>(array[start + k - 1], c.getCount());
  }

  /** builds a min-heap from input array and calls remove-min k times for finding the k-th smallest element.
   * runs at k*logn complexity. */
  public Pair<Integer, Integer> selectHeap(int [] array, int k)
  {
    ComparisonCounter c = new ComparisonCounter();
    MinHeap heap = new MinHeap(array, c);
    int res = heap.getNthElement(k);
    return new Pair<Integer, Integer>(res, c.getCount());
  }

  public Pair<Integer, Integer> selectDoubleHeap(int [] array, int k)
  {
    ComparisonCounter c = new ComparisonCounter();
    MinHeap bigHeap = new MinHeap(array, c);
    array = bigHeap.getArray();
    MinHeap smallHeap = new MinHeap(new int[2*k], 1, new ByIndexComprator(array, c));
    int ind = 0;
    for(int i = 0; i < k; ++i)
    {
      ind = smallHeap.popMin();
      int right = bigHeap.right(ind);
      int left = bigHeap.left(ind);
      if(left < array.length)
      {
        smallHeap.insert(left);
        if(right < array.length) {
          smallHeap.insert(right);
        }
      }
    }
    return new Pair<Integer, Integer>(array[ind], c.getCount()); // to be replaced by student code. (The k'th element,#of comparsion)
  }
  public Pair<Integer, Integer> randQuickSelect(int [] array, int k)
  {
    ComparisonCounter c = new ComparisonCounter();
    Integer selected = quickSelectRec(array, k, 0, array.length, c, PivotMethod.RANDOM);
    return new Pair<Integer, Integer>(selected, c.getCount());
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
      mids[selected] = selectInsertionSort(array, 3, i, i + 5, c).getKey();
      selected++;
    }
    mids[selected] = mid5(array, i, end, c);
    return  nMedians <= 5 ? mid5(mids,0,nMedians, c) : quickSelectRec(mids, (nMedians + 1)/2 ,0,nMedians, c, PivotMethod.MED_OF_MEDS);
  }
//TODO change to private
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
    return size == 2? (array[start] + array[start + 1])/2 : (array[start + 1] + array[start + 2])/2; // in this case array size is 2 or 4
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

  /**
   * simple class for keeping track of #comparisons done. behaves like Comperator. */
  public static class ComparisonCounter{
    private int counter;
    public int comp(Integer a, Integer b){
      counter ++;
      return Integer.compare(a, b);
    }

    // return true iff a < b
    public boolean less(Integer a, Integer b){
      counter ++;
      return a < b;
    }
    public int getCount(){
      return counter;
    }
  }

  /**
   * practically a Comparator which keeps count of the number of comparisons made. recieves an array arr in its constuctor,
   * and when comparing numbers a,b returns a>b iff arr[a]>arr[b]
   * all operations are O(1).
   * */
  private static class ByIndexComprator extends ComparisonCounter{
    int[] arr;
    ComparisonCounter c;

    /** constructor, gets an array arr to compare by, and a counter to use for counting.
     * (the counter inherited from base class is not used, only c is used)
     */
    ByIndexComprator(int[] arr, ComparisonCounter c)
    {
      this.arr = arr;
      this.c = c;
    }

    public int comp(Integer a, Integer b)
    {
      return c.comp(arr[a], arr[b]);
    }

    public boolean less(Integer a, Integer b)
    {
      return c.less(arr[a], arr[b]);
    }

    public int getCount(Integer a, Integer b)
    {
      return c.getCount();
    }
  }

  /**
   * class MinHeap is an implementation of ADT Min-Heap, used in selectHeap and selectDoubleHeap.
   * heap (field) is the underlying array, size is the number of actual elements in the heap
   * (important for avoiding unnecessary comparisons in selectDoubleHeap, algorithm starts with an empty heap).
   * ComparisonCounter is used for maintaining the number of comparisons done.
   * */

  public static class MinHeap{
    int[] heap;
    int size;
    ComparisonCounter c;

    /**
     * Constructor for min heap.
     * elements is an array which the copy of will be the heap's underlying array.
     * size is the number of actual elements, counter is used for comparison counting.
     * Complexity is O(n) for average and worst case, for Build Heap operation.
     * */
    public MinHeap(int[] elements, int size, ComparisonCounter counter){
      heap = Arrays.copyOf(elements, elements.length);
      this.size = size;
      c = counter;

      for(int i = (size / 2); i >= 0; i--){
        heapifyDown(i);
      }
    }

    /**
     * Constructor for min heap.
     * elements is an array which the copy of will be the heap's underlying array.
     * counter is used for comparison counting.
     * This constructor assumes all elements in heap are real elements.
     * Complexity is O(n) for average and worst case, for Build Heap operation.
     * */
    public MinHeap(int[] elements, ComparisonCounter counter){
      this(elements, elements.length, counter);
    }

      /** inserts a new item, num, to the heap as a leaf. assumes underlying array has enough space. recursively goes
       * upwards for preserving the heap property, as long as the current node is smaller than its parent.
       * worst case complexity is O(log(heap size)).
       * average case is O(1) - averaging over all possible inputs, we assume the probability of (num > its new parent) is 0.5,
       * the probability of (num > parent AND num > grandparent) is 0.25, and so on. meaning if heap size was infinite,
       * E[swaps for new element] was expected value of geometric variable with p = 0.5, meaning E[swaps] = 2, making insert
       * O(1).
       * */
      public void insert(int num)
      {
          heap[size] = num;
          size++;
          int i = size - 1;
          int parent = getParentIndex(i);
          while(i >= 0 && parent >= 0 && c.less(heap[i], heap[parent]))
          {
              swap(heap, i, parent);
              i = parent;
              parent = getParentIndex(i);
          }
      }

    //returns n-th smallest element by using n times popMin. average and worst case n*log(heap's size)
    public int getNthElement(int n){
      int min = -1;
      for(int i = 0; i < n; i++)
        min = popMin();
      return min;
    }

    //implement Heap ADT's remove-heap. removes heap's minimum element, returns it, and preserves heap
    // property using heapifyDown. let n be the heap's size, worst case complexity is O(n), which is also the average case
    //  - for heapify we put a leaf x in the root's place and do swaps until heap is fixed. being a leaf means that
    // there are at least log(n) - 1 elements on the path from x to the root, meaning the probable case is O(logn).
    private int popMin(){
      if(size == 1) // size is the number of actual elements in the heap
      {
        size = size - 1;
        return heap[0];
      }
      int min = heap[0];
      heap[0] = heap[size - 1];
      size = size - 1;
      heapifyDown(0);
      return min;
    }

    //returns heap's underlying array. O(1).
    public int[] getArray(){
      return heap;
    }

/**
 * used for fixing heap violation in the case heap[parentIndex] is bigger than one of its children. if a swap is made,
 * we continue recursively downwards. complexity is O(number of swaps made), which is at most O(logn) when n is the heap's size.
 * */

    private void heapifyDown(int parentIndex){
      if(parentIndex < 0)
      {
        return;
      }
      int leftIndex = left(parentIndex);
      int rightIndex = right(parentIndex);
      int n = size;
      int minIndex = parentIndex;
      // minIndex will be != parentIndex iff heap[parentIndex] has a child smaller than itself,
      // meaning a swap has to be made.

      if(leftIndex < n && c.comp(heap[leftIndex], heap[minIndex]) < 0) {
        minIndex = leftIndex;
      }
      if(rightIndex < n && c.comp(heap[rightIndex], heap[minIndex]) < 0) {
        minIndex = rightIndex;
      }
      if(minIndex != parentIndex)
      {
        swap(heap, parentIndex, minIndex);
        heapifyDown(minIndex);
      }
    }

    //returns index of the right child of the item in input index. O(1).
    protected int right(int i){
      return i * 2 + 2;
    }

    //returns index of the left child of the item in input index. O(1).
    protected int left(int i){
      return i * 2 + 1;
    }

    //returns index of the parent of the item in input index. O(1).
    private int getParentIndex(int index){
        return (index + index % 2) / 2 - 1;
    }
  }

}