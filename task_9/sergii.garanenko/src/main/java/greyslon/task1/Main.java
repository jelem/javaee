package greyslon.task1;

public class Main {

  public static void main(String[] args) {
    Counter counter = new Counter(100);
    for (int i = 1; i <= 3; i++) {
      Thread thread = new Thread(new CounterPrinter(counter, i, 3));
      thread.setName("Printer thread-" + i);
      thread.start();
    }
  }
}
