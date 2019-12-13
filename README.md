이 문서는 [우아한 테크코스_프리코스](http://woowabros.github.io/techcourse/2019/10/14/woowacourse.html)의 세번째 미션을 진행하며 정리한 문서입니다.<br>
혹시 문제가 있을 경우 [s26788761@naver.com](s26788761@naver.com)으로 연락주세요!
<br>
<br>

## 기능 요구사항
- 블랙잭 게임을 진행하는 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 플레이어는 게임을 시작할 때 베팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, <br>예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. <br>
21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.<br>
단, 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다.<br>
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5배를 딜러에게 받는다. <br>
딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.<br>
- 딜러는 처음에 받은 2장의 합계가 16 이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.<br>
딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승히애 베팅금액을 받는다.<br>
<br>
<br>


## 프로그래밍 요구사항
- 주어진 Card 객체를 활용해 구현<br>
    Card 기본 생성자 추가 X, symbol과 type 변수의 접근 제어자인 private 변경 X, Card에 필드(인스턴스 변수)를 추가할 수 없다.
- 주어진 Player 객체를 활용해 구현<br>
    Player 기본 생성자 추가 X, name, bettingMoney, cards 변수의 접근 제어자인 private 변경 X, Player에 필드(인스턴스 변수)를 추가할 수 없다.
- 주어진 Dealer 객체를 활용해 구현<br>
    Dealer 기본 생성자 추가 X, cards 변수의 접근 제어자인 private 변경 X, Dealer에 필드(인스턴스 변수)를 추가할 수 없다.

- 자바 코드 컨벤션을 지키며 프로그래밍([좀 더 개선된 컨벤션 참고문서](https://naver.github.io/hackday-conventions-java/))(2주차와 동일)
- 3항 연산자를 사용하지 X(1,2주차와 동일)
- else 예약어 사용 X(2주차와 동일)

- 메소드의 길이가 **10 라인**을 넘지 않도록 구현<br>
    최대한 10라인을 넘지 않기 위해 노력, 정말 힘든경우 15라인까지 허용
    함수가 한 가지 일만 잘 하도록 구현
- indent depth를 **1**까지 허용<br>
    최대한 1을 유지하기 위해 노력하고, 정말 힘든 경우 2까지 허용한다.
- 함수의 인자 수를 3개까지만 허용한다. 4개 이상은 허용하지 X
<br>

`<선택 사항, 필수 아님!>`
- 기본으로 제공하는 Card, Player, Dealer 객체에 예외 처리가 되어 있지 않다. 예외 처리를 한다.
- Player와 Dealer를 구현하다 보면 중복 코드가 발생할 수 있다. 중복 코드를 제거해 본다.<br>
    힌트: 자바의 상속을 활용해 중복을 제거해 본다.

<br>
<br>

## 구현해야 하는 기능
```java
package domain.card;

//카드 한 장을 의미하는 객체
public class Card {
    private final Symbol symbol;

    private final Type type;
    
    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }
	//추가 구현..
}
```

- [ ] 모양 4개 각 13가지 카드 등 정보를 저장하는 상수 설정
- [ ] 생성자에 입력받은 symbol과 type가 유효한지 검사하는 함수
- [ ] 1 ~ 52 사이의(각 모양 별 13장의 카드 * 4가지 모양)숫자를 입력하면 해당하는 카드를 리턴하는 함수
- [ ] 카드의 숫자를 리턴하는 함수
- [ ] 해당 카드가 Ace 인지 체크하는 함수 `isAce()`
- [ ] 문자열로 만들어 리턴하는 함수`toString()`

<br>
<br>

```
package domain.user;

import src.main.java.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

//게임 참여자를 의미하는 객
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

    //추가 구현...
}
```

- [ ] 첫 패에(처음 받은 두 카드) 블랙잭이 되었는지 확인하는 함수
- [ ] 가진 패의 총 합을 계산해 리턴하는 함수
- [ ] 가진 패의 총 합이 21을 넘는지 확인하여 리턴하는 함수
- [ ] 승자인지 패자인지 승자의 값을 입력받아 true false 리턴하는 함수


<br>
<br>

```
package domain.user;

import src.main.java.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

//게임 딜러를 의미하는 객
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {}
    
    public void addCard(Card card) {
        cards.add(card);
    }

    //추가 구현...
}
```

- [ ] 패를 더 받을 지 말지 결정하는 기준값 16에 해당하는 상수
- [ ] 패를 더 받을 지 말지 결정하는 함수
- [ ] 가진 패의 총 합을 계산해 리턴하는 함수
- [ ] 가장 수가 작은 카드를 리턴하는 함수
- [ ] 가진 패의 총 합이 21을 넘는지 확인하여 리턴하는 함수
- [ ] 승자인지 패자인지 승자의 값을 입력받아 true false 리턴하는 함수


<br>
<br>

```
public class CardDeck
```
~~52 장의 카드 덱을 만드는 함수~~<br>
~~1과 52사이 중 겹치지 않는 랜덤 리스트를 만드는 함수~~<br>
- [ ] //셔플(뒤섞는)하는 함수
- [ ] 가장 위에 위치하는 카드를 뽑아서 return 하는 함수

<br>
<br>

```
public class GameManager
```
- [ ] 생성자(players, dealer, cardDeck)
- [ ] 사용자의 이름 초기화 하는 함수
- [ ] 사용자의 이름 중복 확인하는 함수
- [ ] 베팅 금액 초기화 하는 함수
- [ ] 입력받은 사용자 이름 대로 Player객체 만들어서 ArrayList에 추가하는 함수
- [ ] 게임 턴을 진행하는 함수
- [ ] 승자를 구하는 함수

<br>
<br>

```
public class Message
```
- [ ] 플레이어 이름 입력 메시지
- [ ] 베팅 금액 입력 메시지
- [ ] "딜러" 메시지
- [ ] "카드" 메시지
- [ ] "결과" 메시
- [ ] "~에게 2장의 카드 나누었습니다" 메시지
- [ ] "## 최종 수익" 메시 
- [ ] "~는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" 메시지 
- [ ] "딜러는 16 이하라 한 장의 카드를 더 받았습니다." 메시지
- [ ] "딜러는 17 이상이라 한 장의 카드를 더 받지 않았습니다." 메시지

<br>
<br>

```
public class InputManager
```
- [ ] 사용자 이름 입력받는 함수
- [ ] 사용자의 이름 최소 길이 1 이상인지 체크하는 함수
- [ ] 사용자의 베팅 금액 입력 받는 함수
- [ ] 베팅 금액에 문자나 음수가 입력되었는지 체크하는 함수
- [ ] 보기 A 와 B 중 하나의 입력을 사용자에게 받는 함수

<br>
<br>


```
public class Main
```
- [ ] 메인함수

<br>
<br>

### - 고민거리

  ~~1. 1부터 52 사이의 숫자를 입력할때 해당하는 카드를 만드는 함수는 Card 클래스에 위치해아하나 아님 CardDeck 클래스에 위치해야 하나?~~<br>
-> ~~내가 만든 규칙에 따라 1 ~ 52 사이의 정수가 하나 결정되면 해당 카드의 모양과 값이 결정되므로 카드 한장을 나타내는 Card 클래스에서 만드는 것이 좋을 것 같다.~~ <br>
-> CardFactory 클래스에서 트럼프 카드 전체를 생성
<br>
<br>
<br>