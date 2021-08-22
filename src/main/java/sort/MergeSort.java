package sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        Integer[] ay = Arrays.asList(3,2,4,9,5,7,1,8,6).toArray(new Integer[0]);
        System.out.println("排序前");
        Arrays.stream(ay).forEach(System.out::print);

        mergeSort(ay, 0, ay.length - 1);
        System.out.println();
        System.out.println("归并排序");
        Arrays.stream(ay).forEach(System.out::print);
    }

    private static void mergeSort(Integer[] ay, int start, int end) {
        if (start < end) {
            int mid = (end + start) >> 1;
            mergeSort(ay, start, mid);
            mergeSort(ay, mid + 1, end);
            merge(ay, start, mid, end);
        }
    }

    private static void merge(Integer[] ay, int start, int mid, int end) {
        Integer[] temp = new Integer[ay.length];
        int index = start;
        int leftStart = start;
        int endStart = mid + 1;
        while (leftStart <= mid && endStart <= end) {
            if (ay[leftStart] <= ay[endStart]) {
                temp[index++] = ay[leftStart++];
            } else {
                temp[index++] = ay[endStart++];
            }
        }

        while (leftStart <= mid) {
            temp[index++] = ay[leftStart++];
        }

        while (endStart <= end) {
            temp[index++] = ay[endStart++];
        }

        while (start <= end) {
            ay[start] = temp[start++];
        }

    }


    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
