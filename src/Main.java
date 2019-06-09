import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args){
        TestQuickSortSelection();
        TestQuickSelection();
        TestInsertionSort();
        TestMidofMids();
        TestHeap();
        TestDoubleHeap();
        TestHeapSelection();
    }
    private static void TestHeap() {
 /**       TestSelect test = new TestSelect(TestSelect.Func.INCREMENTAL, 1000);
        try {
            test.Test();
        } catch (TestSelect.FailedException e) {
            e.printStackTrace();
            e.print();
        }**/
        int[] arr = {-1,-2};
        SelectProblems.MinHeap heap = new SelectProblems.MinHeap(arr,new SelectProblems.ComparisonCounter());
        printArray(heap.getArray());
        SelectProblems selector = new SelectProblems();
        System.out.print(selector.selectHeap(arr,1));
    }

    private static void TestDoubleHeap() {
        TestSelect test = new TestSelect(TestSelect.Func.DOUBLE_HEAP, 1000);
        try {
            test.Test();
        } catch (TestSelect.FailedException e) {
            e.printStackTrace();
            e.print();
        }
        int[] arr = {5, 9, 11, 7, 4, 2, 1, 3, 10, 8, 6};
        SelectProblems selector = new SelectProblems();
        System.out.println(selector.selectDoubleHeap(arr, 2));

    }

    private static void TestMidofMids() {
        TestSelect test = new TestSelect(TestSelect.Func.MED_OF_MEDS, 100);
        try {
            test.Test();
        } catch (TestSelect.FailedException e) {
            e.printStackTrace();
            e.print();
        }
    }

    private static void TestQuickSortSelection() {
        SelectProblems selector = new SelectProblems();
        int[] a = {-10, 45, 232, 223, 2, 0, 0, 0, 13, 12, 14, 12, 13, 1, 3, 5, 66, -34, -23, -22};
        int[] sorted = a.clone();
        Arrays.sort(sorted);
        for(int i = 0; i < a.length; i++){
            assert sorted[i] == selector.selectRandQuickSort(a, i + 1).getKey();
        }
        TestSelect test = new TestSelect(TestSelect.Func.QUICKSORT, 100);
        try {
            test.Test();
        } catch (TestSelect.FailedException e) {
            e.printStackTrace();
            e.print();
        }
    }
    private static void TestQuickSelection() {
        SelectProblems selector = new SelectProblems();
        int[] a = {-10, 45, 232, 223, 2, 0, 0, 0, 13, 12, 14, 12, 13, 1, 3, 5, 66, -34, -23, -22};
        int[] sorted = a.clone();
        Arrays.sort(sorted);
        for(int i = 0; i < a.length; i++){
            assert sorted[i] == selector.randQuickSelect(a, i + 1).getKey();
        }

        TestSelect test = new TestSelect(TestSelect.Func.RAND_QUICKSELECT, 1000);
        try {
            test.Test();
        } catch (TestSelect.FailedException e) {
            e.printStackTrace();
            e.print();
        }
    }

    private static void TestInsertionSort() {
     TestSelect test = new TestSelect(TestSelect.Func.INSERTION, 100);
     try {
         test.Test();
     } catch (TestSelect.FailedException e) {
         e.printStackTrace();
     }
    }

    private static void printArray(int[] arr)
    {
        for(int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println("");
    }
    private static void TestHeapSelection() {
        TestSelect test = new TestSelect(TestSelect.Func.HEAP, 1000);
        try {
            test.Test();
        } catch (TestSelect.FailedException e) {
            e.printStackTrace();
            e.print();
        }
        int[] arr = {2, 4, 3, 5, 1};
        SelectProblems.MinHeap heap = new SelectProblems.MinHeap(arr, new SelectProblems.ComparisonCounter());
    }


    public static class TestSelect{
        private Func func;
        private TestType type = TestType.NORMAL;
        private SelectProblems selector;

        private Pair<Integer, Integer> select(int[] arr, int k)
        {
            switch (func)
            {
                case INSERTION:
                    return selector.selectInsertionSort(arr, k);
                case RAND_QUICKSELECT:
                    return selector.randQuickSelect(arr, k);
                case QUICKSORT:
                    return selector.selectRandQuickSort(arr, k);
                case MED_OF_MEDS:
                    return selector.medOfMedQuickSelect(arr, k);
                case HEAP:
                    return selector.selectHeap(arr, k);
                case DOUBLE_HEAP:
                    return  selector.selectDoubleHeap(arr, k);
                case INCREMENTAL:
                    return  selector.selectIncrementalHeap(arr,k);
            }
            return null;
        }
        enum Func
        {
            INSERTION,
            RAND_QUICKSELECT,
            QUICKSORT,
            MED_OF_MEDS,
            HEAP,
            DOUBLE_HEAP,
            INCREMENTAL
        }

        enum TestType
        {
            NORMAL,
            BUILDHEAP
        }

        private static int[] randomList(int size)
        {
            ArrayList<Integer> list = new ArrayList(createList(size));

            Collections.shuffle(list);
            int[] ret = new int[size];
            for(int i = 0; i < size; ++i)
                ret[i] = list.get(i);
            return ret;
        }

        private static int[] asArray(List<Integer> list)
        {
            int size = list.size();
            int[] ret = new int[size];
            for(int i = 0; i < size; ++i)
                ret[i] = list.get(i);
            return ret;
        }
        private static ArrayList<Integer> createList(int size)
        {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                list.add(i);
            }
            return list;
        }

        int upTo;

        TestSelect(Func func, int upTo)
        {
            this(func, upTo, TestType.NORMAL);
        }
        TestSelect(Func func, int upTo, TestType type)
        {
            this.func = func;
            this.upTo = upTo;
            this.type = type;
            selector = new SelectProblems();
/**            type = TestType.SELECTION;

            if(func == Func.QUICKSORT || func == Func.INSERTION)
            {
                type = TestType.SORT;
            }
            if(func == Func.HEAP)
            {
                type = TestType.HEAP;
            }**/
        }

        void Test() throws FailedTestException, FailedWithException {
            List<Integer> lst = new ArrayList<>();
            int[] arr;
            int[] sorted;
            for(int i = 1; i <= upTo; ++i)
            {
                lst.add(i);
                Collections.shuffle(lst);
                arr = asArray(lst);
                sorted = arr.clone();
                Arrays.sort(sorted);
                for(int j = 0; j < arr.length; ++j)
                {
                    Integer res = null;
                    try{
                        res = select(arr, j+1).getKey();
                    }catch (Exception e){
                        throw new FailedWithException(arr, sorted, sorted[j], j+1, e);
                    }

                    if(Integer.compare(sorted[j], res) != 0)
                    {
                        throw new FailedTestException(arr, sorted, sorted[j], res, j+1);
                    }
                }
            }
        }

        private class FailedTestException extends FailedException {
            int[] arr = null;
            int[] sorted = null;
            Integer result = null;
            Integer expected = null;
            int k;

            public FailedTestException(int[] arr, int[] sorted, Integer expected, Integer result, int k) {
                this.arr = arr;
                this.sorted = sorted;
                this.result = result;
                this.expected = expected;
                this.k = k;
            }

            public void print() {
                System.out.println("result array:");
                for(int i = 0; i < arr.length; ++i)
                    System.out.print(arr[i] + " ");
                System.out.println("");
                System.out.println("sorted array:");
                for(int i = 0; i < sorted.length; ++i)
                    System.out.print(sorted[i] + " ");
                System.out.println("");
                System.out.println("result " + result);
                System.out.println("expected " + expected);
                System.out.println("k " + k);

            }
        }
        private abstract class FailedException extends Throwable {
            public abstract void print();
        }

        private class FailedWithException extends FailedException {
            int expected;
            Exception e;
            int[] arr;
            int[] sorted;
            int k;

            public FailedWithException(int[] arr, int[] sorted, int expected, int k, Exception e) {
                this.arr = arr;
                this.sorted = sorted;
                this.expected = expected;
                this.k = k;
                this.e = e;
            }

            @Override
            public void print() {
                e.printStackTrace();
                System.out.println("result array:");
                for(int i = 0; i < arr.length; ++i)
                    System.out.print(arr[i] + " ");
                System.out.println("");
                System.out.println("sorted array:");
                for(int i = 0; i < sorted.length; ++i)
                    System.out.print(sorted[i] + " ");
                System.out.println("");
                System.out.println("expected " + expected);
                System.out.println("k " + k);
            }
        }
/**        static void TestCase(int[] arr, int k, int expected)
        {

        }**/
    }

}

