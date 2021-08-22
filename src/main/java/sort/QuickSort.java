package sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        //冒泡排序
        Integer[] ay = Arrays.asList(3,2,4,9,5,7,1,8,6).toArray(new Integer[0]);
        System.out.println("排序前");
        Arrays.stream(ay).forEach(System.out::print);
//        bubbleSort(ay);
//        System.out.println("冒泡排序");
//        Arrays.stream(ay).forEach(System.out::print);

        quickSort2(ay, 0, ay.length - 1);
        System.out.println();
        System.out.println("快速排序");
        Arrays.stream(ay).forEach(System.out::print);
    }

    public static void quickSort2(Integer[] arr, int left, int right) {
        if (left < right) {
            int key = arr[left]; // 基准数字
            int index = left; // 基准数字待交换的位置
            for (int i = left + 1; i <= right; i++) {
                if (key > arr[i]) {
                    index++;
                    swap(arr, i, index);
                }
            }
            // 基准数字放到指定位置
            arr[left] = arr[index];
            arr[index] = key;

            quickSort2(arr, left, index - 1);
            quickSort2(arr, index + 1, right);

        }
    }


    public static void quickSort(Integer[] array, int left, int right) {
        if (left < right) {
            int mid = quickIndex(array, left, right);
            quickSort(array, left, mid - 1);
            quickSort(array, mid + 1, right);
        }
    }

    private static int quickIndex(Integer[] array, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (array[i] < array[pivot]) {
                swap(array, i, index);
                index++;
            }
        }
        swap(array, pivot, index - 1);
        return index - 1;
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
