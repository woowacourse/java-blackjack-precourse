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
    최대한 10라인을 넘지 않기 위해 노력, 정말 힘든경우 15라인까지 허용<br>
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
- 문자열로 만들어 리턴하는 함수`toPrintString()`
- 카드의 숫자를 리턴하는 함수 `getSymbolValue()`
- 해당 카드가 Ace 인지 체크하는 함수 `isAce()`

<br>
<br>

```
public class Symbol
```

- 해당 Symbol에 해당하는 문자열 리턴하는 함수 `toString()`
<br>
<br>

```
public class Type
```
- 해당 Type에 해당하는 문자열 리턴하는 함수 `toString()`
<br>
<br>


```
public class CardDeck
```
- *//가장 위에 있는 카드 리턴하는 함수*
- *//카드 패 섞는 함수*
- ->Card 리스트 중 랜덤 index에 있는 Card 리턴하는 함수`getRandomCard()`
- 이미 사용된 Card인지 확인하는 함수 `isUsed()`
- 0 ~ 'Card 리스트의 사이즈' 사이에 있는 랜덤값 리턴하는 함수 `getRandomIndex()`

<br>
<br>
<br>
<br>
<br>
<br>





```
public class GameManager
```
- 처음에 카드 나눠주는 수에 해당하는 상수 `START_CARD_COUNT`,<br>
 블랙잭에 해당하는 상수 `BLACK_JACK`,<br>
 점수가 블랙잭보다 큰 경우 -1 처리 하는 상수 `OVERFLOW`
- 생성자 `GameManager()`
- 게임 진행하는 함수 `start()`
- Player 정의하는 함수 `initializePlayers()`
- 카드 2장 Player들과 Dealer에게 주는 함수 `drawTwoCardsToDealerAndPlayer()`
- Player에게 카드 2장 주는 함수 `drawTwoCardsToPlayer()`
- 메시지 출력하는 함수 `print()`
- Dealer와 Player들의 카드 리스트 출력해주는 함수 `showCardLists()`
- Player들과 Dealer에게 추가 패 받게 하는 함수 `getMoreCards()`
- Player에게 추가 패 받을건지 질문하는 함수 `askPlayerDrawOrNot()`
- Dealer가 추가 패 받게하는 함수 `checkDealerDrawOrNot()`
- Dealer가 조건에 만족할 때 까지 추가 패 받게하는 함수 `dealerDrawMore()`
- 최종 카드 패 출력하는 함수 `showFinalCardLists()`
- 최종 손익 출력하는 함수 `showProfit()`
- 승자의 점수 구하는 함수 `getWinnerScore()`
- Player들의 최고 점수 구하는 함수 `getPlayersMaxScore()`
- 두 값 중 21보다 작은 가장 큰 값을 리턴하는 함수 `getBiggerValueUnderBlackJack()`
       

<br>
<br>


```
public class InputManager
```
- 이름의 최소 갯수 해당하는 상수 `MINIMUM_NAME_COUNT`<br>
이름의 최소 길이 해당하는 상수 `MINIMUM_NAME_LENGTH`<br>
숫자 0에 해당하는 상수 `ZERO`<br>
사용자의 "y"입력에 해당하는 상수 `YES`<br>
사용자의 "n"입력에 해당하는 상수 `NO`<br>
<br>

- 사용자 이름을 입력받는 함수 `getPlayerNames()`
- 이름이 유효한지 체크하는 함수 `checkNamesValid()`
- String[]을 ArrayList<String>으로 변환하는 함수 `makeArrayToArrayList()`
- 입력받은 문자열 배열의 좌우 공백 제거하는 함수 `trimWhiteSpace()`
- 이름의 갯수 유효한지 체크하는 함수 `checkNamesCountValid()`
- 이름들의 길이 유효한지 체크하는 함수 `checkNamesLengthValid()`
- 이름의 길이 유효한지 체크하는 함수 `checkNameLengthValid()`
- 이름이 중복되지 않았는지 체크하는 함수 `checkNamesDuplicated()`
- ArrayList에 문자열 포함되어 있는지 확인하는 함수 `listContainsString()`

<br>

- 베팅할 금액 입력받는 함수 `getPlayerBattingMoney()`
- 베팅할 금액이 유효한지 체크하는 함수 `checkBattingMoneyValid()`
- 베팅할 금액이 문자를 포함하는지 체크하는 함수 `checkBattingMoneyContainsChar()`
- 베팅할 금액이 음수인지 체크하는 함수 `checkBattingMoneyIsMinus()`
- 베팅할 금액이 0인지 체크하는 함수 `checkBattingMoneyIsZero()`

<br>

- Player에게서 추가 패를 받을지 말지 입력받는 함수 `getPlayerChoiceDrawOneMoreOrNot()`
- 사용자의 입력이 "y" 혹은 "n" 인지 체크하는 함수 `checkPlayerInputIsYesOrNo()`

<br>
<br>


```
public class Message
```
- 각종 메시지를 나타내는 상수 및 함수 
- 이하 생략...

<br>
<br>

```
public class ErrorMessage
```
- 입력된 플레이어 이름이 하나도 없을 때 에러메시지에 해당하는 상수 `PLAYER_IS_NOT_ENTERED`
- 입력된 플레이어 이름의 길이가 0일 때 에러메시지에 해당하는 상수 `PLAYER_NAME_IS_TOO_SHORT`
- 입력된 플레이어 이름이 중복되었을 때 에러메시지에 해당하는 상수 `PLAYER_NAME_IS_DUPLICATED`
- 입력된 베팅 금액에 문자가 포함되었을 때 에러메시지에 해당하는 상수 `PLAYER_BATTING_MONEY_CONTAINS_CHAR`
- 입력된 베팅 금액이 음수일 경우 에러메시지에 해당하는 상수 `PLAYER_BATTING_MONEY_IS_MINUS`
- 입력된 베팅 금액이 0일 경우 에러메시지에 해당하는 상수 `PLAYER_BATTING_MONEY_IS_ZERO`
- 입력이 "y" 또는 "n"가 아닐 경우 에러메시지에 해당하는 상수 `INPUT_IS_NOT_YES_OR_NO`

<br>
<br>
<br>
<br>
<br>
<br>




```
package domain.user;

import src.main.java.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

//게임 참여자를 의미하는 객체
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
- ACE가 카드 패에 있을 경우 추가 점수를 나타내는 상수`ACE_ADDITIONAL_SCORE` <br>
패자 일 경우 곱하는 -1을 나타내는 상수`MINUS` <br>
기본 베팅률을 나타내는 상수`BASIC_BATTING_RATIO` <br>
첫 두 패에 블랙잭이 나올 경우의 베팅률을 나타내는 상수 `BIGGER_BATTING_RATIO` <br>
- 생성자 `Player()`
- 카드를 추가하는 함수 `addCard()`
- ACE를 고려한 최종 점수를 리턴하는 함수 `getScore()`
- 카드패에 ACE가 있는지 확인하는 함수 `cardsContainsAce()`
- 순수하게 카드의 점수만 합하는 함수 `getCardsPoint()`
- 카드패를 문자열로 만들어 리턴하는 함수 `getCardListString()`
- 최고 점수를 입력받아 승자인지 아닌지 true, false 리턴하는 함수 `isWinner()`
- 승패를 고려하여 최종적으로 번 돈 리턴하는 함수 `getEarnMoney()`
- 이름 리턴하는 함수 `getName()`

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

- Dealer가 추가 패를 받을지 말지 기준이 되는 상수`DEALER_CRITERIA` <br>
ACE가 카드 패에 있을 경우 추가 점수를 나타내는 상수`ACE_ADDITIONAL_SCORE` <br>
- 생성자 `Dealer()`
- 카드를 추가하는 함수 `addCard()`
- ACE를 고려한 최종 점수를 리턴하는 함수 `getScore()`
- 카드패에 ACE가 있는지 확인하는 함수 `cardsContainsAce()`
- 순수하게 카드의 점수만 합하는 함수 `getCardsPoint()`
- 카드패를 문자열로 만들어 리턴하는 함수 `getCardListString()`
- 추가 패를 받아야 하면 true 리턴, 아니면 false 리턴하는 함수 `getMoreDraw()`
- 가장 크기가 작은 Card를 리턴하는 함수 `getSmallestCard()`
- 승패와 Player들을 고려하여 최종적으로 번 돈 리턴하는 함수 `getEarnMoney()`


<br>
<br>
<br>
<br>
<br>
<br>



``` 
public class Main
```
-  메인함수

<br>
<br>
<br>
<br>
<br>