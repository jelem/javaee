package greyslon.task3;


import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

  CustomBlockingQuery queue;
  List<Thread> threads = new ArrayList<>();
  private boolean shutdown;

  public ThreadPool(int numberOfThreads) {
    queue = new CustomBlockingQuery(2);

    for (int i = 0; i < numberOfThreads; i++) {
      ThreadInPool threadInPool = new ThreadInPool(queue);
      Thread thread = new Thread(threadInPool);
      threads.add(thread);
    }
  }

  public void start() {
    threads.forEach(thread -> thread.start());
  }

  public void execute(Runnable task) {
    try {
      if (shutdown) {
        throw new InterruptedException();
      }
      queue.addTask(task);
    } catch (InterruptedException ex) {
      System.out.println("Task is rejected");
    }
  }

  public void terminate() {
    shutdown = true;
    for (Thread thread : threads) {
      thread.interrupt();
    }
  }
}