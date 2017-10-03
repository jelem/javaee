package greyslon.task1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

  private int stopValue;
  private AtomicInteger value = new AtomicInteger(1);
  private AtomicBoolean stopped = new AtomicBoolean(false);

  public Counter(int stopValue) {
    this.stopValue = stopValue;
  }

  public int getCurrentValue() {
    return value.intValue();
  }

  public void increase() {
    if (value.get() < stopValue) {
      value.incrementAndGet();
    } else {
      stopped.set(true);
    }
  }

  public boolean isStopped() {
    return stopped.get();
  }
}
