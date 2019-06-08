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
    }

    private static void TestMidofMids() {
        TestSelect test = new TestSelect(TestSelect.Func.MED_OF_MEDS, 1000);
        try {
            test.Test();
        } catch (TestSelect.FailedTestException e) {
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
    }
    private static void TestQuickSelection() {
        SelectProblems selector = new SelectProblems();
        int[] a = {-10, 45, 232, 223, 2, 0, 0, 0, 13, 12, 14, 12, 13, 1, 3, 5, 66, -34, -23, -22};
        int[] sorted = a.clone();
        Arrays.sort(sorted);
        for(int i = 0; i < a.length; i++){
            assert sorted[i] == selector.randQuickSelect(a, i + 1).getKey();
        }

        int[] arr = {2,1,3};
        System.out.println(selector.randQuickSelect(arr, 1));
        System.out.println(selector.randQuickSelect(arr, 2));
        System.out.println(selector.randQuickSelect(arr, 3));
    }

    private static void TestInsertionSort() {
     TestSelect test = new TestSelect(TestSelect.Func.INSERTION, 100);
     try {
         test.Test();
     } catch (TestSelect.FailedTestException e) {
         e.printStackTrace();
     }
    }


    public static class TestSelect{
        private Func func;
//        private TestType type;
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
            DOUBLE_HEAP
        }

/**        enum TestType
        {
            SELECTION,
            SORT,
            HEAP
        }**/

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
            this.func = func;
            this.upTo = upTo;
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

        void Test() throws FailedTestException {
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
                    Integer res = select(arr, j+1).getKey();
                    if(Integer.compare(sorted[j], res) != 0)
                    {
                        throw new FailedTestException(arr, sorted, sorted[j], res, j+1);
                    }
                }
            }
        }

        private class FailedTestException extends Throwable {
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
    }

}

