import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        TestQuickSortSelection();
        TestQuickSelection();
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
}
