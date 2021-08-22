package sort;

import java.util.Arrays;

/**
 * 排序
 */
public class InsertSort {

    public static void main(String[] args) {
        //选择排序
        Integer[] ay = Arrays.asList(3,2,4,9,5,7,1,8,6).toArray(new Integer[0]);
        System.out.println("排序前");
        Arrays.stream(ay).forEach(System.out::print);
        System.out.println();
        insertSort(ay);
        System.out.println("排序");
        Arrays.stream(ay).forEach(System.out::print);
    }


    /**
     * 选择排序
     * @param array
     */
    public static void insertSort(Integer[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            int j = i;
            int temp = array[i];
            for (; j > 0; j--) {
                if (temp < array[j - 1]) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = temp;
        }
    }

}
