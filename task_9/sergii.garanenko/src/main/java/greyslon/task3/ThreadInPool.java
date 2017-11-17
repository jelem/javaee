package greyslon.task3;

public class ThreadInPool implements Runnable {

  private CustomBlockingQuery queue;

  public ThreadInPool(CustomBlockingQuery queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        Runnable task = queue.pollTask();
        task.run();
      }
      System.out
          .println(Thread.currentThread().getName() + " is interrupted on checking interrupt flag");
    } catch (InterruptedException ex) {
      System.out.println(Thread.currentThread().getName() + " is interrupted with exception");
    }
  }
}