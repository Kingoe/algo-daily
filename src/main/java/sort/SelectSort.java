package sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        //选择排序
        Integer[] ay = Arrays.asList(3,2,4,9,5,7,1,8,6).toArray(new Integer[0]);
        System.out.println("排序前");
        Arrays.stream(ay).forEach(System.out::print);
        selectSort(ay);
        System.out.println("选择排序");
        Arrays.stream(ay).forEach(System.out::print);
    }


    /**
     * 选择排序
     * @param array
     */
    public static void selectSort(Integer[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < length; j++) {
                if (array[i] > array[j]) {
                    index = j;
                }
            }
            if (i != index) {
                swap(array, i, index);
            }
        }
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
