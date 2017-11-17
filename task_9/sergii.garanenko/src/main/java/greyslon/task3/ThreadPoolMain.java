package greyslon.task3;

import java.util.Random;

public class ThreadPoolMain {

  private static final Random rand = new Random();

  public static void main(String[] args) throws InterruptedException {

    ThreadPool threadPool = new ThreadPool(2);
    threadPool.start();
    for (int i = 0; i < 10; i++) {

      threadPool.execute(() ->
          System.out.println(Thread.currentThread().getName() + " - task executed"));
      if (rand.nextInt(5) == 2) {
        threadPool.terminate();
      }
    }
  }
}