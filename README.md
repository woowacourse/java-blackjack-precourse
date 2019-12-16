# 3주차 미션 - 블랙잭

## 프로그래밍 요구사항

### 1,2주차와 동일

* **자바 코드 컨벤션을 지키면서 프로그래밍 한다.**
  * [Naver Hackday Convention](https://naver.github.io/hackday-conventions-java/) 더 개선된 컨벤션 참고
* **3항 연산자를 사용하지 않는다.**
* **else 예약어를 쓰지 않는다.**
  * Hint: if 조건절에거 값을 return하는 방식으로 구현하면 된다.
  * switch/case도 허용하지 않는

### 3주차 변경 및 추가

* **함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.**
  * **최대한 10라인을 넘기지 않기 위해 노력하고, 정말 힘든 경우 15라인까지 허용**한다.
  * 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
* **indent(들여쓰기) depth 2가 넘지 않도록 구현**한다. **1까지만 허용**한다.
  * **최대한 1을 유지하기 위해 노력하고, 정말 힘든 경우 2까지 허용**한다.
* **함수(또는 메소드)의 인자 수를 3개까지만 허용**한다. 4개 이상은 허용하지 않는다.

### 선택 요구사항

* 기본으로 제공하는 Card, Player, Dealer 객체에 예외 처리가 되어 있지 않다. 예외 처리를 한다.
* Player와 Dealer를 구현하다보면 중복 코드가 발생할 수 있다. 중복 코드를 제거해 본다.
  * Hint: **자바의 상속을 활용해 중복을 제거해 본다.**

### 객체1

* 다음 Card 객체를 활용해 구현해야 한다.
* Card 기본 생성자를 추가할 수 없다.
* 필드(인스턴스 변수)인 symbol과 type의 접근 제어자인 private을 변경할 수 없다.
* Card에 필드(인스턴스 변수)를 추가할 수 없다

```java
public class Card {
    private final Symbol symbol;
    
    private final Type type;
    
    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }
    //추가 기능 구현
}
```

### 객체 2

* 다음 Player 객체를 활용해 구현해야 한다.
* Player 기본 생성자를 추가할 수 없다.
* 필드인 name, bettingMoney, cards의 접근 제어자 private을 변경할 수 없다.
* Player에 필드를 추가할 수 없다.

```java
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    
    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    //추가 기능 구현
}
```

### 객체 3

* 다음 Dealer 객체를 활용해 구현해야 한다.
* Dealer 기본 생성자 이외 다른 생성자를 추가할 수 없다.
* 필드인 cards의 접근 제어자 private을 변경할 수 없다.
* Dealer에 필드를 추가할 수 없다.

```java
public class Dealer {
    private final List<Card> cards = new ArrayList<>();
    
    public Dealer() {}
    
    public void addCard(Card card) {
        cards.add(card);
    }
    //추가 기능 구현
}
```

---

### 기능 요구사항

* 블랙잭 게임을 진행하는 프로그램을 구현한다.
  블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
* 플레이어는 게임을 시작할 때 베팅 금액을 정해야 한다.
  카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
* 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
  21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
   단, 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다.
* 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면서 베팅 금액의 1.5배를 딜러에게 받는다.
  딜러와 플레리어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려 받는다.
* 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
  딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 베팅 금액을 받는다.

----

### 구현할 기능 목록

* 게임은 참여할 Player들의 이름을 입력 받는다.
  * 이름은 쉼표기준으로 분리해야한다.
    * 이름에 공백은 포함할 수 없다.
* 각 Player의 bettingMoney를 입력 받는다.
  * bettingMoney는 0 보다 크다.
* 처음 "블랙잭"(2장의 합이 21) 인 경우 베팅액의 1.5배를 받는다.
  * 딜러와 플레이어가 모두 동시에 블랙잭인 경우 베팅 금액만 받는다.
* 각 플레이어가 카드를 추가로 받을지 입력 받는다.
  * 입력은 y,n이다.
    * 이외의 입력은 IllegalArgumentException 로 예외처리한다.
  * 받았을 경우 21을 초과하게 되면 베팅 금액을 모두 잃는다.
* 딜러는 한장의 카드를 공개한다.
  * 딜러의 값이 16 이하면 반드시 한 장을 추가로 받고, 17이상이면 받을 수 없다.
  * 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 베팅 금액을 받는다.

**선택**

* Player와 Dealer의 조상 객체(Candidates)를 구현해 코드 중복을 처리한다.
* 각 객체의 예외를 처리한다.
