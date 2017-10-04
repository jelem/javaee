package greyslon.task4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import org.junit.Test;

public class MergeSortRecursiveTaskTest {

  private ForkJoinPool forkJoinPool = new ForkJoinPool();


  @Test
  public void oneThreadComputing() {
    Random rand = new Random();
    int[] arr = new int[90];
    Arrays.setAll(arr, operand -> rand.nextInt(1000));

    MergeSortRecursiveTask task = new MergeSortRecursiveTask(arr);
    forkJoinPool.invoke(task);
    int[] sortedByMSRT = task.join();
    Arrays.sort(arr);
    assertThat(arr).containsExactly(sortedByMSRT);
  }

  @Test
  public void forkedComputing() {
    Random rand = new Random();
    int[] arr = new int[200];
    Arrays.setAll(arr, operand -> rand.nextInt(1000));

    MergeSortRecursiveTask task = new MergeSortRecursiveTask(arr);
    forkJoinPool.invoke(task);
    int[] sortedByMSRT = task.join();
    Arrays.sort(arr);
    assertThat(arr).containsExactly(sortedByMSRT);
  }
}