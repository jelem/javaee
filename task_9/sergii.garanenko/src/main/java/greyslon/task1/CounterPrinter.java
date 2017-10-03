package greyslon.task1;

public class CounterPrinter implements Runnable {

  private int order;
  private int index;
  private Counter counter;

  public CounterPrinter(Counter counter, int index, int order) {
    this.counter = counter;
    this.index = index;
    this.order = order;
  }

  @Override
  public void run() {

    try {
      while (!counter.isStopped()) {
        if (counter.getCurrentValue() % index == 0) {
          System.out.println(
              String.format("%3d - %s",
                  counter.getCurrentValue(),
                  Thread.currentThread().getName()));
          index += order;
          counter.increase();
        }
        Thread.sleep(50);
      }
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }
}
