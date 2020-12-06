package modernjavainaction.chap02;

public class MeaningOfThis {

  public final int value = 4;

  public void doIt() {
    int value = 6;
    Runnable r = new Runnable() {
      public final int value = 5;
      @Override
      public void run() {
        int value = 10;
        System.out.println(this.value);
      }
    };
    r.run();
  }

  //익명 클래스에서 바라보는 값
  public static void main(String... args) {
    MeaningOfThis m = new MeaningOfThis();
    m.doIt(); // ??? -> 5임
  }

}
