import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args){
        TestQuickSortSelection();
       TestMidofMids();
        TestQuickSelection();
        TestInsertionSort();
    }

    private static void TestMidofMids() {
        SelectProblems selector = new SelectProblems();
        int[] a1 = {-10};
        int[] a2 = {-10,45};
        int[] a3 = {-10, 45, 232};
        int[] a4 = {-10, 45, 232, 223};
        int[] a5 = {-10, 45, 232, 223, 2};

        //System.out.println(selector.mid5(a1, 0, 1, null));
        //System.out.println(selector.mid5(a2, 0, 2, null));
        //System.out.println(selector.mid5(a3, 0, 3, null));
        //System.out.println(selector.mid5(a4, 0, 4, null));
        //System.out.println(selector.mid5(a5, 0, 5, null));

        int[] a = {-10, 45, 232, 223, 2, 0, 0, 0, 13, 12, 14, 12, 13, 1, 3, 5, 66, -34, -23, -22};
        int[] sorted = a.clone();
        Arrays.sort(sorted);
        for(int i = 0; i < 19; i++){
            try {
                assert sorted[i] == selector.medOfMedQuickSelect(a, i).getKey();
            }catch (Exception e){
                System.out.println(i);
                return;
            }
        }
    }

    private static void TestQuickSortSelection() {
        SelectProblems selector = new SelectProblems();
        int[] a = {-10, 45, 232, 223, 2, 0, 0, 0, 13, 12, 14, 12, 13, 1, 3, 5, 66, -34, -23, -22};
        int[] sorted = a.clone();
        Arrays.sort(sorted);
        for(int i = 0; i < a.length; i++){
            assert sorted[i] == selector.selectRandQuickSort(a, i).getKey();
        }
    }
    private static void TestQuickSelection() {
        SelectProblems selector = new SelectProblems();
        int[] a = {-10, 45, 232, 223, 2, 0, 0, 0, 13, 12, 14, 12, 13, 1, 3, 5, 66, -34, -23, -22};
        int[] sorted = a.clone();
        Arrays.sort(sorted);
        for(int i = 0; i < a.length; i++){
            assert sorted[i] == selector.randQuickSelect(a, i).getKey();
        }
    }

    private static void TestInsertionSort() {
        int[] numbers = randomList(5);
        SelectProblems selector = new SelectProblems();
        int[] sorted = numbers.clone();
        Arrays.sort(sorted);
        selector.selectInsertionSort(numbers, 5);
        assert Arrays.equals(sorted, numbers);
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

    private static ArrayList<Integer> createList(int size)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            list.add(i);
        }
        return list;
    }

}
