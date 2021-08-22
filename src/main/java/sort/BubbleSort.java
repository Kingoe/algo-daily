package sort;

import java.util.Arrays;

import static com.sun.tools.javac.jvm.ByteCodes.swap;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        //冒泡排序
        Integer[] ay = Arrays.asList(3,2,4,9,5,7,1,8,6).toArray(new Integer[0]);
        System.out.println("排序前");
        Arrays.stream(ay).forEach(System.out::print);
//        bubbleSort(ay);
//        System.out.println("冒泡排序");
//        Arrays.stream(ay).forEach(System.out::print);

        bubbleSort2(ay);
        System.out.println("冒泡排序");
        Arrays.stream(ay).forEach(System.out::print);
    }

    public static void bubbleSort2(Integer[] array) {
        int count = array.length - 1;
        while (count > 0) {
            for (int i = 0; i < count; i++) {
                if (array[i] < array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
            count--;
        }
    }

    /**
     * 冒泡
     * @param array
     */
    public static void bubbleSort(Integer[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
