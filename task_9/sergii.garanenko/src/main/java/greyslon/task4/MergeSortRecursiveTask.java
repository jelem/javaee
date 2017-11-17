package greyslon.task4;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class MergeSortRecursiveTask extends RecursiveTask<int[]> {

  private int[] initArray;

  public MergeSortRecursiveTask(int[] initArray) {
    this.initArray = Arrays.copyOf(initArray, initArray.length);
  }

  @Override
  protected int[] compute() {
    if (initArray.length < 100) {
      return mergeSort(initArray);
    }

    MergeSortRecursiveTask firstPart = new MergeSortRecursiveTask(
        Arrays.copyOfRange(initArray, 0, initArray.length / 2));
    MergeSortRecursiveTask secondPart = new MergeSortRecursiveTask(
        Arrays.copyOfRange(initArray, initArray.length / 2, initArray.length));

    invokeAll(firstPart, secondPart);
    return merge(firstPart.join(), secondPart.join());
  }

  private static int[] mergeSort(int[] arr) {
    if (arr.length < 2) {
      return arr;
    }
    int middle = arr.length / 2;
    int[] left = Arrays.copyOfRange(arr, 0, middle);
    int[] right = Arrays.copyOfRange(arr, middle, arr.length);
    return merge(mergeSort(left), mergeSort(right));
  }

  private static int[] merge(int[] left, int[] right) {
    int[] result = new int[left.length + right.length];
    int il = 0;
    int ir = 0;
    int iresult = 0;
    while (il < left.length && ir < right.length) {
      if (left[il] < right[ir]) {
        result[iresult++] = left[il++];
      } else {
        result[iresult++] = right[ir++];
      }
    }
    while (il < left.length) {
      result[iresult++] = left[il++];
    }
    while (ir < right.length) {
      result[iresult++] = right[ir++];
    }
    return result;
  }
}
