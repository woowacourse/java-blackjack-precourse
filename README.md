# 자바 블랙잭 게임

## 실행 순서

1. 플레이어를 참가시키기 위해 이름과 베팅할 금액을 입력받는다.

2. 게임을 시작한다. 먼저  새 카드뭉치를 깐다.

3. 카드를 섞는다.

4. 카드 뭉치의 위에서부터 한 장씩을 뽑아 딜러와 플레이어들이 2장씩 가진다.

5. 먼저 딜러가 블랙잭인지 확인한다. 딜러가 블랙잭이면 플레이어도 블랙잭이어야만 방어할 수 있다.

6. 딜러가 블랙잭이 아니면 첫 번째 플레이어부터 카드를 한 장씩 더 뽑을 수 있다. 카드 뽑기를 그만하면 다음 플레이어에게 턴이넘어간다.

7. 딜러는 카드의 합이 16을 넘을 때까지 계속 카드를 뽑는다.

8. 딜러와 플레이어 모두 카드를 뽑던 도중 합이 21을 넘으면 버스트가 되어 게임 오버된다.

9. 카드를 뽑을 때 에이스가 나오면 플레이어에게 유리한 쪽으로 자동으로 1 또는 11로 계산된다.

   (예: 스페이드 8, 클럽 A를 뽑고 끝내면 19로 계산된다. 그러나 이 상태에서 하트 5, 스페이드 7을 추가로 뽑으면 1로 취급해 21점이 된다.)

10. 모든 플레이어들이 자신의 턴을 끝내면 결과가 집계된다. 점수는 기본적으로 딜러 <-> 각 플레이어간만 비교하며, 베팅한 금액을 이긴 쪽이 가져간다. 단 플레이어가 블랙잭으로 이기면 1.5배를 가져간다.

## 구현된 기능 목록

| 기능                                                         | '클래스명.메소드명'                    | 입력                      | 출력                  |
| ------------------------------------------------------------ | -------------------------------------- | ------------------------- | --------------------- |
| 게임의 정보를 얻는다.                                        | BlackJackGame(생성자)                  | 게임 참여자들             | 없음                  |
| 게임 참가자를 받는다.                                        | Player(생성자)                         | 이름, 베팅할 금액         | 없음                  |
| 블랙잭 게임을 시작한다.                                      | BlackJackGame.startGame()              | 없음                      | 없음                  |
| 새 카드뭉치를 섞어서 뒤집어 놓는다.                          | BlackJackGame.shuffleCards()           | 없음                      | 카드 뭉치             |
| 플레이어의 목록을 출력한다.                                  | BlackJackGame.getPlayerNames()         | 없음                      | 플레이어 목록 String  |
| 시작할 때 받는 카드 2개씩을 나눠준다.                        | BlackJackGame.drawStartCards()         | 카드 뭉치                 | 없음                  |
| 카드 뭉치의 맨 위에 있는 카드 하나를 뽑는다.                 | BlackJackGame.drawTopCard()            | 카드 뭉치                 | 카드 1장              |
| 플레이어들이 카드를 더 뽑을지 말지 선택한다.                 | BlackJackGame.startTurn()              | 카드 뭉치                 | 없음                  |
| 플레이어가 N을 누를 때까지 계속 하나 더 뽑을지 물어본다.     | BlackJackGame.drawContinuously()       | 플레이어, 카드 뭉치       | 없음                  |
| 각각의 플레이어가 카드를 더 뽑을지 말지 질문에 응답한다.     | BlackJackGame.askToHit()               | 플레이어, 카드 뭉치       | true/false            |
| 플레이어와 딜러의 점수를 비교하여 배당액을 나누어 준다.      | BlackJackGame.putBettingMoney()        | 플레이어, 딜러, 딜러 잔고 | 플레이어 잔고         |
| 최종 수익을 발표한다.                                        | BlackJackGame.announceWinner()         | 없음                      | 최종 수익             |
| 점수에 따라 누가 얼마나의 수익을 가져가야하는지 계산한다.    | BlackJackGame.getShare()               | 플레이어, 딜러            | 배당액                |
| 딜러와 플레이어별 점수, 버스트 여부, 블랙잭 여부를 출력한다. | BlackJackGame.announceDetailedResult() | 없음                      | 없음                  |
| 카드를 하나 뽑는다.                                          | Person.addCard()                       | Card 객체                 | 없음                  |
| 게임 진행 중에 카드 목록을 조회한다.                         | Person.getCardString()                 | 없음                      | 카드 목록             |
| 게임 결과 발표 때 카드 목록을 조회한다.                      | Person.getFinalCardString()            | 없음                      | 카드 목록             |
| 각 카드의 한글 이름을 리턴한다.                              | Person.getKoreanName()                 | 없음                      | 카드 이름             |
| 카드의 점수를 합산 계산한다. A는 상황에 따라 1 또는 11로 계산한다. | Person.calculateScore()                | 없음                      | 점수                  |
| 플레이어/딜러가 버스트 되었는지 리턴한다.                    | Person.isBusted()                      | 없음                      | true/false            |
| 플레이어/딜러가 블랙잭인지 리턴한다.                         | Person.isBlackJack()                   | 없음                      | true/false            |
| 딜러의 카드 합계가 16 이하인지 검사한다.                     | Dealer.isBelowRedraw()                 | 없음                      | true/false            |
| 카드의 한글 이름을 리턴한다.                                 | Card.toKorean()                        | 없음                      | 카드 이름             |
| 이 카드가 에이스인지 여부를 리턴한다.                        | Card.isAce()                           | 없음                      | true/false            |
| 카드의 전체 리스트를 만든다.                                 | CardFactory.create()                   | 없음                      | 카드 뭉치             |
| 게임에 참여할 사람 이름을 받는다.                            | UserInput.inputPlayersname()           | 없음                      | 플레이어 이름         |
| 리스트의 형태로 플레이어 이름들을 나눈다. 이때 이름이 모두 유효한지 검사한다. | UserInput.splitPlayersName()           | 플레이어 이름             | 플레이어 이름(리스트) |
| 베팅할 돈을 입력받는다.                                      | UserInput.inputBettingMoney()          | 플레이어 이름             | 금액                  |
| 예/아니오로 답하는 부분은 전부 여기서 처리한다.              | UserInput.inputYesNo()                 | 없음                      | true/false            |



## 배당 집계

기본적으로 블랙잭은 개개인 플레이어와 딜러 간의 대결이며, 플레이어 간에는 상호 영향을 끼치지 않는다.

| 조건                                 | 이득  |
| ------------------------------------ | ----- |
| 딜러가 플레이어보다 21에 가까운 경우 | -100% |
| 플레이어가 딜러보다 21에 가까운 경우 | +100% |
| 플레이어와 딜러가 같은 점수인 경우   | 0%    |
| 딜러만 블랙잭인 경우                 | -100% |
| 플레이어만 블랙잭인 경우             | +150% |
| 둘 다 블랙잭인 경우                  | 0%    |
| 딜러만 버스트된 경우                 | +100% |
| 플레이어만 버스트된 경우             | -100% |
| 둘 다 버스트된 경우                  | 0%    |



## 예외 처리

| 예외 상황                          | 처리 방법                                                    |
| ---------------------------------- | ------------------------------------------------------------ |
| 이름에 아무것도 입력하지 않을 경우 | playersName.equals("") -> IllegalArgumentException()         |
| 이름에 공백이 있을 경우            | playerName.contains("") -> IllegalArgumentException()        |
| 베팅 금액이 숫자가 아닌 경우       | UserInput.inputBettingMoney(name) -> IllegalArgumentException() |
| 카드를 다 뽑았을 경우              | 카드가 소진되지 않도록, 8명의 플레이어만 받는다.             |
| 8명 이상의 플레이어가 들어올 경우  | playerName.size() > 8 -> IllegalArgumentException()          |

## 알게 된 점

* 3주차를 시작하기 전에 MVC 패턴에 대하여 공부했다. MVC 패턴에 입각한 설계를 하기위해 최대한 노력해 보았다.
* 이런 복잡한 프로그램은 처음부터 어느 정도 모델-뷰-컨트롤러 파일을 나눠 두고 시작하는 것도 도움이 되는 것 같다.
* `Collections.shuffle()` 로 카드를 섞듯이 리스트를 섞을 수 있다. 
* `Stream`과 `forEach`, 람다식을 이용하면 코드가 많이 깔끔해진다.



----

## 기능 요구 사항

* 블랙잭 게임을 진행하는 프로그램을 구현한다. 

- [x] 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- [x] 플레이어는 게임을 시작할 때 베팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며,
- [x] 예외로 Ace는 1 또는 11로 계산할 수 있으며,
- [x] King, Queen, Jack은 각각 10으로 계산한다.
- [x] 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며,
- [x] 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
- [x] 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.(히트)
- [x] 단, 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다. (버스트)
- [x] 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
- [x] 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
- [x] 딜러는 처음에 받은 2장의 합계가 16 이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 
- [x] 딜러가 21을 초과하면 (버스트) 그 시점까지 남아 있던 (버스트당하지 않은) 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

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

- [x] 다음 Card 객체를 활용해 구현해야 한다.
- [x] Card 기본 생성자를 추가할 수 없다.
- [x] 필드(인스턴스 변수)인 symbol과 type의 접근 제어자 private을 변경할 수 없다.
- [x] Card에 필드(인스턴스 변수)를 추가할 수 없다.

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

- [x] 다음 Player 객체를 활용해 구현해야 한다.
- [x] Player 기본 생성자를 추가할 수 없다.
- [x] 필드(인스턴스 변수)인 name, bettingMoney, cards의 접근 제어자 private을 변경할 수 없다.
- [x] Player에 필드(인스턴스 변수)를 추가할 수 없다.

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

- [x] 다음 Dealer 객체를 활용해 구현해야 한다.
- [x] Dealer 기본 생성자 이외 다른 생성자를 추가할 없다.
- [x] 필드(인스턴스 변수)인 cards의 접근 제어자 private을 변경할 수 없다.
- [x] Dealer에 필드(인스턴스 변수)를 추가할 수 없다.

## 프로그래밍 요구사항 (선택)

- [ ] 기본으로 제공하는 Card, Player, Dealer 객체에 예외 처리가 되어 있지 않다. 예외 처리를 한다.
- [x] Player와 Dealer를 구현하다보면 중복 코드가 발생할 수 있다. 중복 코드를 제거해 본다.
  * 힌트: 자바의 상속을 활용해 중복을 제거해 본다.

## 프로그래밍 요구사항 (필수)

- [x] 자바 코드 컨벤션을 지키면서 프로그래밍한다.
  - https://naver.github.io/hackday-conventions-java/ : 좀 더 개선된 컨벤션 참고 문서

* [x] 3항 연산자를 쓰지 않는다.
* [x] else 예약어를 쓰지 않는다.
  * 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
  * else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case 도 허용하지 않는다.
* [x] 함수(또는 메소드)의 길이가 10라인 을 넘어가지 않도록 구현한다.
  * 최대한 10라인을 넘지 않기 위해 노력하고, 정말 힘든 경우 15라인까지 허용한다.
  * 함수(또는 메소드)가 한 가지 일만 잘 하도록 구현한다.
* [x] indent(인덴트, 들여쓰기) depth를 2 가 넘지 않도록 구현한다. 1 까지만 허용한다.
  * 최대한 1을 유지하기 위해 노력하고, 정말 힘든 경우 2까지 허용한다.
  * 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
* [x] 함수(또는 메소드)의 인자 수를 3개까지만 허용한다. 4개 이상은 허용하지 않는다.