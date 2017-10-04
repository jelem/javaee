package greyslon.task3;

import java.util.LinkedList;
import java.util.Queue;

public class CustomBlockingQuery {

  private Queue<Runnable> queue;

  private int capacity;

  public CustomBlockingQuery(int capacity) {
    this.capacity = capacity;
    queue = new LinkedList<>();
  }

  public synchronized void addTask(Runnable task) throws InterruptedException {
    while (queue.size() >= capacity) {
      System.out.println("adding waits");
      wait();
    }
    System.out.println("task added");
    queue.add(task);
    notifyAll();
  }

  public synchronized Runnable pollTask() throws InterruptedException {
    Runnable task;
    while (queue.size() == 0) {
      wait();
    }

    task = queue.poll();
    notifyAll();

    return task;
  }

}