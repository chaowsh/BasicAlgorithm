package cn.edu.fudan.cs.sort;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class Solution {
    /***
     * TOP K problems
     * 1. get least K numbers - build 大顶推
     * 2. get largest K numbers - build 小顶堆
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || k <= 0 || arr.length <= k) return arr;
        //原地建K个元素的大顶堆
        buildHeap(arr, k);
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > arr[0]) continue;
            swap(arr, i, 0);
            adjustHeap(arr, k, 0);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public void buildHeap(int []arr, int k) {
        for (int i = k/2 - 1; i >=0; i--) {
            adjustHeap(arr, k, i);
        }
    }

    public void adjustHeap(int []arr, int k, int i) {
        int largest = i;
        while (true) {
            int leftChild = 2*i + 1;
            if (leftChild < k && arr[leftChild] > arr[i]) largest = leftChild;
            int rightChild = 2*i + 2;
            if (rightChild < k && arr[rightChild] > arr[largest]) largest = rightChild;
            if (arr[largest] == arr[i]) break;
            swap(arr, i, largest);
            i = largest;
        }
    }

    public void swap(int []arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /***
     * 通过快排解决
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || k <= 0 || arr.length <= k) return arr;
        //k-1表示要找数组中第(k-1)th index
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    int []quickSearch(int []arr, int low, int high, int k) {
        //j表示左边的数据都小于arr[j], 右边的数据都大于arr[j]
        int j = partiton(arr, low, high);
        if (k == j) return Arrays.copyOf(arr, k + 1);
        return j > k? quickSearch(arr, low, j - 1, k ): quickSearch(arr, j + 1, high, k);
    }

    int partiton(int []arr, int lo, int hi) {
        //pick the latest element as piovt element
        int piovt = arr[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (arr[j] <= piovt) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, hi);
        return  i + 1;
    }



    public static void main(String []args) {
        Solution s = new Solution();
        int arr[] = {0,0,1,2,4,2,2,3,1,4};
        int res[] = s.getLeastNumbers2(arr, 8);
        for(int i=0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
