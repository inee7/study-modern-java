# modern-java 책을 보며 공부하는 저장소 

*내맘대로 노트*


함수형인터페이스에서 기본형 특화를 쓰면 언박시이 되지 않아 메모리 유리 

함수형 디스크립터 (람다 표현식의 시그니처) 

형식추론 

자유변수로 람다캡쳐링 → 람다에서 외부변수를 가져다 씀 (지역변수는 final이어야하고 정적변수와 인스턴스변수는 그냥 쓰면 됨)

Comparator인터페이스로 비교하는데 

compare(Function ) 정적 메소드로 객체에 특정 필드를 기준으로 비교대상을 정할 수 있다.

List객체.sort(Comparator 구현체) 로 정렬 가능 

Comparator 유틸 메소드로 추가 기능 가능 revesed()... thenComparing()..  

Predicate 유틸 

```
Predicate<Apple> redApple = apple -> apple.getColor().equals(Color.RED);
Predicate<Apple> notRedApple = redApple.negate(); // 반전 가능
Predicate<Apple> heavy = apple -> apple.getWeight() > 150; // 반전 가능
Predicate<Apple> andPre = heavy.and(notRedApple); // predicate 조합가능 
```

Function 유틸 

```
Function<Apple, Integer> f = Apple::getWeight;
Function<Apple, Byte> appleByteFunction = f.andThen(Integer::byteValue);
```

스트림은 선언적이다. 

선언적이라는건 더 간결하고 가독성이 좋음 

내부반복을 한다. 

병렬도 알아서 한다. 

 

## 5장. 스트림활용

- filter
- distinct
- takeWhile 조건이 거짓전까지만 _**java9**
- dropWhile 조건이 거짓이후 부터만 _**java9**
- limit
- skip 해당 갯수만큼 스킵
- flatmap // 스트림으로 반환 해줘야
- allMatch, anyMatch, noneMatch

쇼트서킷이라는 연산자 limit, allMatch.... 는 and처럼 결과를 다 보지 않아도 처리 할 수 있는 역할을 해서 무한 스트림에서 자주 쓴다. 

- findFirst, findAny (병렬일때 꼭 이거 써라)
- 리듀싱연산을 FP에서는 폴드라고 부른다.
    - reduce(초깃값, BinaryOperator)
    - reduce(BinaryOperator) 오버로딩 함수도 있긴한데 반환값이 Optional
    - numbers.stream().reduce(Integer::max) 최댓값
    - reduce를 쓰면 외부 반복을 통해 공유변수로 누적했던것을 넘어 병렬에 유리하다.
- 기본 특화형 스트림에서만 sum, max, average... 쓸 수 있다.
- IntStream.boxed();
- max() 연산 했을때 OptionalInt 처럼 반환해서 값이 없을때를 대응한다.
-