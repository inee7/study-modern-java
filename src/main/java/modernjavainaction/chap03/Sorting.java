package modernjavainaction.chap03;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Sorting {

  public static void main(String... args) {
    // 1
    List<Apple> inventory = new ArrayList<>();
    inventory.addAll(Arrays.asList(
        new Apple(80, Color.GREEN),
        new Apple(155, Color.GREEN),
        new Apple(120, Color.RED)
    ));

    // [Apple{color=GREEN, weight=80}, Apple{color=RED, weight=120}, Apple{color=GREEN, weight=155}]
    inventory.sort(new AppleComparator());
    System.out.println(inventory);

    // reshuffling things a little
    inventory.set(1, new Apple(30, Color.GREEN));

    // 2
    // [Apple{color=GREEN, weight=30}, Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
    inventory.sort(new Comparator<Apple>() {

      @Override
      public int compare(Apple a1, Apple a2) {
        return a1.getWeight() - a2.getWeight();
      }
    });
    System.out.println(inventory);

    // reshuffling things a little
    inventory.set(1, new Apple(20, Color.RED));

    // 3
    // [Apple{color=RED, weight=20}, Apple{color=GREEN, weight=30}, Apple{color=GREEN, weight=155}]
    inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
    System.out.println(inventory);

    // reshuffling things a little
    inventory.set(1, new Apple(10, Color.RED));

    // 4
    // [Apple{color=RED, weight=10}, Apple{color=RED, weight=20}, Apple{color=GREEN, weight=155}]
    inventory.sort(comparing(Apple::getWeight));
    System.out.println(inventory);

    inventory.sort(
            comparing(Apple::getWeight)
            .reversed() // 역순
            .thenComparing(Apple::getColor) //같으면 이걸로 비교
    );

    Predicate<Apple> redApple = apple -> apple.getColor().equals(Color.RED);
    Predicate<Apple> notRedApple = redApple.negate(); // 반전 가능
    Predicate<Apple> heavy = apple -> apple.getWeight() > 150; // 반전 가능
    Predicate<Apple> andPre = heavy.and(notRedApple); // predicate 조합가능

    Function<Apple, Integer> f = Apple::getWeight;
    Function<Apple, Byte> appleByteFunction = f.andThen(Integer::byteValue);

  }

  static class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple a1, Apple a2) {
      return a1.getWeight() - a2.getWeight();
    }

  }

}
