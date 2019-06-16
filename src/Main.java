import javafx.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {/**
        int[] arr = IntStream.rangeClosed(1, 5).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1));
        IntStream.rangeClosed(1, 20).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1));
        arr = IntStream.rangeClosed(1, 25).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1));
        arr= IntStream.rangeClosed(1, 50).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1));
        arr= IntStream.rangeClosed(1, 100).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1).getValue()/100);
        arr= IntStream.rangeClosed(1, 200000).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1).getValue()/200000);
        arr= IntStream.rangeClosed(1, 20000000).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1).getValue()/20000000);
        arr= new Random().ints(20000000, 0, 20000000).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1).getValue()/20000000);**/
        //       printArray(arr);
        DoTests();
/**        MeasureTest test= new MeasureTest(TestSelect.Func.HEAP).SetRandomArray(131072);
 test.SetRandomArray(100000).SetK(1).printResult();
 test.SetFunc(TestSelect.Func.DOUBLE_HEAP).printResult();

 test.SetK(16).SetFunc(TestSelect.Func.HEAP).printResultWithoutBaseHeap();
 test.SetFunc(TestSelect.Func.DOUBLE_HEAP).printResultWithoutBaseHeap();
 test.SetK(16*16).SetFunc(TestSelect.Func.HEAP).printResultWithoutBaseHeap();
 test.SetFunc(TestSelect.Func.DOUBLE_HEAP).printResultWithoutBaseHeap();
 test.SetK(16*16*8).SetFunc(TestSelect.Func.HEAP).printResultWithoutBaseHeap();
 test.SetFunc(TestSelect.Func.DOUBLE_HEAP).printResultWithoutBaseHeap();
 test.SetK(16*16*8*8).SetFunc(TestSelect.Func.HEAP).printResultWithoutBaseHeap(); // *3574
 test.SetFunc(TestSelect.Func.DOUBLE_HEAP).printResultWithoutBaseHeap();        **/
      //  printEverything();/**
        SelectProblems selector = new SelectProblems();
        int[] arr = IntStream.rangeClosed(1, 2000).toArray();
        int[] arr2 = IntStream.rangeClosed(1, 200000).toArray();
        int base = selector.selectDoubleHeap(arr,1).getValue();
        int base2 = selector.selectDoubleHeap(arr2,1).getValue();
        System.out.println(base);
        System.out.println(base2);
        System.out.println(selector.selectDoubleHeap(arr, 5).getValue()-base);
        System.out.println(selector.selectDoubleHeap(arr2, 5).getValue()-base2);
        System.out.println(selector.selectDoubleHeap(arr, 2000/2).getValue()-base);
        System.out.println(selector.selectDoubleHeap(arr2, 200000/2).getValue()-base2);
/**
        IntStream.rangeClosed(1, 2000).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 2000/70));
        arr = IntStream.rangeClosed(1, 200000).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1));
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 5) - );
        IntStream.rangeClosed(1, 200000).toArray();
        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 200000/70));**/
/**        int len = 16383;
        int[] arr = IntStream.rangeClosed(1, len).toArray();
        for(int i = 1; i < 16383; i*=2)
            System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, len/i));

        len =(int) (16383 * 1.5);
        arr = IntStream.rangeClosed(1, len).toArray();
        for(int i = 1; i < len; i*=2)
            System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, len/i));

        len =(int) (16383 * 0.5);
        arr = IntStream.rangeClosed(1, len).toArray();
        for(int i = 1; i < len; i*=2)
            System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, len/i));

        len =(int) (10000);**/
//        arr = IntStream.rangeClosed(1, 10000 - 1).toArray();
//        arr = MeasureTest.loadArrayStatic("rand" + 10000 + ".txt");
        //for(int i = 1; i < len; i*=10)
//            System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1));

//        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, 1));
 //       System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, len));
  //      System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, len / 2));

        //        System.out.println((new SelectProblems()).medOfMedQuickSelect(arr, len / 2 - len / 4));
    }

        private static void printEverything()
        {
                PrintStream p = System.out;
         try {
             TestSelect.Func func = TestSelect.Func.HEAP;


             TestSelect.Func[] funcs = {TestSelect.Func.INSERTION,
                     TestSelect.Func.QUICKSORT, TestSelect.Func.HEAP,
                     TestSelect.Func.DOUBLE_HEAP,TestSelect.Func.RAND_QUICKSELECT, TestSelect.Func.MED_OF_MEDS};
             int ind = Arrays.asList(funcs).indexOf(func);
             String[] fnames = new String[]{"insertion.txt", "quicksort.txt", "heap.txt", "doubleheap.txt", "quickselect.txt", "medofmeds.txt"};
         PrintStream f = new PrintStream(fnames[ind]);
         System.setOut(f);
         for(int i = 1; i <= 10; ++i)
         {
            everything(10000 * i, func);
         }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         }
         System.setOut(p);
        }

    private static void everything(int size, TestSelect.Func func)
    {
        //        printMeasure(TestSelect.Func.INSERTION, MeasureTest.ArrayType.RANDOM, 1);
//        printMeasure(TestSelect.Func.QUICKSORT, MeasureTest.ArrayType.RANDOM, 10);

        TestSelect.Func[] funcs = {TestSelect.Func.INSERTION,
                TestSelect.Func.QUICKSORT, TestSelect.Func.HEAP,
                TestSelect.Func.DOUBLE_HEAP,TestSelect.Func.RAND_QUICKSELECT, TestSelect.Func.MED_OF_MEDS};
        int[] times = new int[]{1,50,1,1,50,1};
        boolean[] bigs = new boolean[]{true, false, false, false, false, false};
        MeasureTest test = new MeasureTest(TestSelect.Func.INSERTION);
        long[] results = new long[6];

        int ind = Arrays.asList(funcs).indexOf(func);
        System.out.println("for size " + size);
        for(int j = 0; j <= 10; ++j)
        {
            if(j <= 10)
            {
                test.loadArray("rand"+size+".txt");
            }else{
                test.SetRandomArray(size);
            }

            for(int i = 0; i < funcs.length; ++i)
            {
                if(i != ind)
                    continue;
                test.SetFunc(funcs[i]);
         //       test.randomize = true;
                test.k = (j == 0)? 1: j * size/10;
                long res = test.TestCase(bigs[i], times[i]);
                results[i] += res;
                System.out.println( test.k + " " +  res + "\n withouth base: ");
                test.printResultWithoutBaseHeap();
            }
        }
        System.out.println("for size " + size);
        for(int i = 0; i < funcs.length; ++i) {
            System.out.println("avg for " + funcs[i] + " " + results[i] / 11);
        }
        System.out.println(System.lineSeparator());
//        System.out.println(test.TestCase(MeasureTest.ArrayType.RANDOM, 100000, true));
    }
    private static void printMeasure(TestSelect.Func func, MeasureTest.ArrayType type, int times) {
        MeasureTest test = new MeasureTest(func);
        long[] res = test.Measure(type);
        for(int i = 1; i < times; ++i)
        {
            long[] temp =  test.Measure(type);
            for(int j = 0; j < 10; ++j)
            {
                res[j] += temp[j];
            }
        }
        for(int j = 0; j < 10; ++j)
        {
            res[j] /= times;
        }
        System.out.println("ran " + func.name() + " on " + type.name() + " input for " + times + "times");
        printArray(res);
    }

    private static void DoTests() {
        TestQuickSortSelection();
        TestQuickSelection();
      //  TestInsertionSort();
        TestMidofMids();
        TestHeap();
        TestDoubleHeap();
      //  TestHeapSelection();
    }

    private static void TestHeap() {
        TestSelect test = new TestSelect(TestSelect.Func.HEAP, 1000);
        try {
            test.Test();
        } catch (TestSelect.FailedException e) {
            e.printStackTrace();
            e.print();
        }
/**        int[] arr = {-1,-2};
        SelectProblems.MinHeap heap = new SelectProblems.MinHeap(arr,new SelectProblems.ComparisonCounter());
        printArray(heap.getArray());
        SelectProblems selector = new SelectProblems();
        System.out.print(selector.selectHeap(arr,1));**/
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

    private static void printArray(long[] arr)
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
        public Func func;
        public TestType type = TestType.NORMAL;
        public SelectProblems selector;

        public Pair<Integer, Integer> select(int[] arr, int k) {
            return select(arr, k, new SelectProblems.ComparisonCounter());
        }

        public Pair<Integer, Integer> select(int[] arr, int k, SelectProblems.ComparisonCounter c)
        {
            switch (func)
            {
                case INSERTION:
                    return selector.selectInsertionSort(arr, k, 0, arr.length, c);
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
            DOUBLE_HEAP,
            INCREMENTAL
        }

        enum TestType
        {
            NORMAL,
            BUILDHEAP
        }

        public static int[] randomList(int size)
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
    }

    public static class MeasureTest extends TestSelect{

        boolean randomize = false;
        int[] arr;
        int k;
        long result;
        int times = 1;
        boolean big = false;
        BufferedWriter writer;
        boolean write = false;
        static HashMap<String, int[]> ArraysMap;

        {
            ArraysMap = new HashMap<>();
        }

        static void makeArray(int size)
        {
            ObjectOutputStream outputStream = null;
            try {
                outputStream = new ObjectOutputStream(new FileOutputStream("rand" + size + ".txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.writeObject(randomList(size));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static int[] loadArrayStatic(String fname)
        {
            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(new FileInputStream(fname));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                return (int[])inputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        void loadArray(String fname)
        {
            if(ArraysMap.containsKey(fname))
            {
                arr = ArraysMap.get(fname);
            }else{
                arr = loadArrayStatic(fname);
            }
        }

        MeasureTest SetFunc(Func func)
        {
            this.func = func;
            return this;
        }
        MeasureTest(Func func) {
            super(func, 0);
        }
        MeasureTest(Func func, int upTo) {
            super(func, upTo);
        }

        enum ArrayType
        {
            SORTED,
            RANDOM,
            ANTISORTED
        }

/**        public long TestCase(ArrayType type, int size, boolean big)
        {
            long sum = 0;
            int[] arr = createArray(type, size);
            for(int k = 1; k < size; k += size/10)
            {
                if(big)
                {
                    SelectProblems.LongCounter counter = new SelectProblems.LongCounter();
                    select(arr.clone(), k, counter);
                    sum += counter.getLongCcount();
                }else {
                    sum += select(arr.clone(), k).getValue();
                }
                //printArray(arr);
                result = sum;
            }
            return sum / 9;
        }**/

        MeasureTest SetBig(boolean big)
        {
            this.big = big;
            return this;
        }

        MeasureTest SetWrite(boolean write)
        {
            this.write = write;
            return this;
        }

        MeasureTest SetTimes(int times)
        {
            this.times = times;
            return this;
        }
        MeasureTest SetRandomArray(int size, int bound)
        {
            arr = new Random().ints(size, 0, bound).toArray();
            return this;
        }

        MeasureTest SetRandomArray(int size)
        {
            arr = new Random().ints(size,0,size).toArray();
            return this;
        }

        public void printResult()
        {
            System.out.println(TestCase(false));
        }
        public long TestCase(boolean big, int times)
        {
            long sum = 0;
            for(int i = 0; i < times; ++i)
            {
                sum += TestCase(big);
            }
            result = sum / times;
            return sum / times;
        }

        public long TestCase(boolean big)
        {
            long sum = 0;
            int size = arr.length;
            //for(int k = 1; k < arr.length; k += size/10)
            //{
            if(randomize)
            {
                SetRandomArray(size, 10000);
            }
                if(big)
                {
                    LongCounter counter = new LongCounter();
                    select(arr.clone(), k, counter);
                    sum += counter.getLongCcount();
                }else {
                    sum += select(arr.clone(), k).getValue();
                }
                //printArray(arr);
            //}**/
            result = sum;
            return sum;
        }

        public MeasureTest SetK(int k)
        {
            this.k=k;
            return this;
        }

    //    public void SetSize(int size)
  //      {
 //           this.size = size;
//        }
        private long[] Measure(ArrayType type)
        {
            long[] res = new long[10];
            for(int i = 1; i <= 10; i++)
            {
                int n = i*10000;
                int sum = 0;
                int times = 0;
                for(int k = 1; k < n; k += n/10)
                {
                    int[] arr = createArray(type, n);
                    sum += select(arr, k).getValue();
                    times++;
                }
                res[i-1] = sum / times;
            }
            return res;
        }


        public void printResultWithoutBaseHeap()
        {
            int oldK = this.k;
            Func oldFunc = func;
            long res = doTest().getResult();
            long res2 = SetK(1).SetFunc(Func.DOUBLE_HEAP).doTest().getResult();
            System.out.println(res - res2);
            this.k = oldK;
            func = oldFunc;
        }

        public MeasureTest doTest()
        {
            TestCase(false);
            return this;
        }

        private long getResult() {
            return result;
        }

        private int[] createArray(ArrayType type, int size) {
            return randomList(size);
        }

    }

    public static class LongCounter extends SelectProblems.ComparisonCounter{
        long longCcount = 0;
        public int comp(Integer a, Integer b)
        {
            ++longCcount;
            return a - b;
        }
        public boolean less(Integer a, Integer b)
        {
            ++longCcount;
            return a < b;
        }

        long getLongCcount()
        {
            return longCcount;
        }
    }
}

