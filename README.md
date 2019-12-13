# 자바 블랙잭 게임

## 구현할 기능 목록

| 고정 | 기능                                           | 클래스명                          | 입력                | 출력                  |
| ---- | ---------------------------------------------- | --------------------------------- | ------------------- | --------------------- |
| x    | 게임의 정보를 얻는다.                          | BlackJackGame(생성자)             | 게임 참여자들       | 없음                  |
| v    | 게임 참가자를 받는다.                          | Player(생성자)                    | 이름, 배팅할 금액   | 없음                  |
| v    | 카드를 하나 뽑는다.                            | addCard()                         | Card 객체           | 없음                  |
| x    | 카드의 전체 리스트를 만든다.                   | CardFactory.create()              | 없음                | 카드 뭉치             |
| x    | 블랙잭 게임을 시작한다.                        | BlackJackGame.startGame()         | 없음                | 없음                  |
| x    | 시작할 때 받는 카드 2개씩을 나눠준다.          | BlackJackGame.drawStartCards()    | 카드 덱             | 없음                  |
| x    | 현재 카드 상태를 출력한다.                     | View.PrintCardStatus()            | 참여자 또는 딜러    | 화면에 카드 상태 출력 |
| x    | 점수가 21인 딜러나 플레이어가 있는지 확인한다. | BlackJackGame.checkBlackjack()    | 딜러, 플레이어 목록 | 블랙잭 존재 여부      |
| x    | 한 장의 카드를 더 뽑을 것인지 물어본다.        | BlackJackGame.askForOneMoreCard() | y 또는 n            | 없음                  |
| x    | 딜러의 카드 합계가 16 이하인지 검사한다.       | Dealer.isBelowRedraw()            | 없음                | true, false           |
| x    | 플레이어/딜러가 버스트 되었는지 출력한다.      | isBusted()                        | 없음                | 버스트 여부           |



## 배당 집계

| 조건                                 | 이득  |
| ------------------------------------ | ----- |
| 플레이어가 딜러보다 21에 가까운 경우 | +100% |
| 플레이어가 딜러보더 21에 먼 경우     | -100% |
| 플레이어와 딜러가 같은 점수인 경우   | 0%    |
| 딜러가 블랙잭인 경우                 | -100% |
| 플레이어가 블랙잭인 경우             | +150% |
| 둘 다 블랙잭인 경우                  | 0%    |
| 딜러가 버스트된 경우                 | +100% |
| 플레이어가 버스트된 경우             | -100% |
| 둘 다 버스트된 경우                  | 0%    |



## 기능 요구 사항

* 블랙잭 게임을 진행하는 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
* 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
* 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.(히트) 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다. (버스트)
* 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
* 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

## 프로그래밍 요구사항 - 객체 1

```java
package domain.card;

/**
* 카드 한장을 의미하는 객체
*/
public class Card {
	private final Symbol symbol;
	
	private final Type type;
	
	public Card(Symbol symbol, Type type) {
		this.symbol = symbol;
		this.type = type;
	}
	
	// TODO Card 관련 추가 기능 구현
}
```

* 다음 Card 객체를 활용해 구현해야 한다.
* Card 기본 생성자를 추가할 수 없다.
* 필드(인스턴스 변수)인 symbol과 type의 접근 제어자 private을 변경할 수 없다.
* Card에 필드(인스턴스 변수)를 추가할 수 없다.

## 프로그래밍 요구사항 - 객체 2

```java
package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
* 게임 참여자를 의미하는 객체
*/
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
	
	// TODO 추가 기능 구현
}
```

* 다음 Player 객체를 활용해 구현해야 한다.
* Player 기본 생성자를 추가할 수 없다.
* 필드(인스턴스 변수)인 name, bettingMoney, cards의 접근 제어자 private을 변경할 수 없다.
* Card에 필드(인스턴스 변수)를 추가할 수 없다.

## 프로그래밍 요구사항 - 객체 3

```java
package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
* 게임 딜러를 의미하는 객체
*/
public class Dealer {
	private final List<Card> cards = new ArrayList<>();

	public Dealer() {}

	public void addCard(Card card) {
		cards.add(card);
	}
	// TODO 추가 기능 구현
}
```

* 다음 Dealer 객체를 활용해 구현해야 한다.
* Dealer 기본 생성자 이외 다른 생성자를 추가할 없다.
* 필드(인스턴스 변수)인 cards의 접근 제어자 private을 변경할 수 없다.
* Dealer에 필드(인스턴스 변수)를 추가할 수 없다.

## 프로그래밍 요구사항 (선택)

* 기본으로 제공하는 Card, Player, Dealer 객체에 예외 처리가 되어 있지 않다. 예외 처리를 한다.
* Player와 Dealer를 구현하다보면 중복 코드가 발생할 수 있다. 중복 코드를 제거해 본다.
  * 힌트: 자바의 상속을 활용해 중복을 제거해 본다.

## 프로그래밍 요구사항 (필수)

* 자바 코드 컨벤션을 지키면서 프로그래밍한다.
  * https://naver.github.io/hackday-conventions-java/ : 좀 더 개선된 컨벤션 참고 문서
* 3항 연산자를 쓰지 않는다.
* else 예약어를 쓰지 않는다.
  * 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
  * else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case 도 허용하지 않는다.
* 함수(또는 메소드)의 길이가 10라인 을 넘어가지 않도록 구현한다.
  * 최대한 10라인을 넘지 않기 위해 노력하고, 정말 힘든 경우 15라인까지 허용한다.
  * 함수(또는 메소드)가 한 가지 일만 잘 하도록 구현한다.
* indent(인덴트, 들여쓰기) depth를 2 가 넘지 않도록 구현한다. 1 까지만 허용한다.
  * 최대한 1을 유지하기 위해 노력하고, 정말 힘든 경우 2까지 허용한다.
  * 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
* 함수(또는 메소드)의 인자 수를 3개까지만 허용한다. 4개 이상은 허용하지 않는다.