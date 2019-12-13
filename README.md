# 우아한테크코스 프리코스 3주차 과제 - 블랙잭 (19.12.11 수 ~ 17 화)
## 기능 요구사항

##### • 블랙잭 게임을 진행하는 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21 에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
##### • 플레이어는 게임을 시작할 때, 배팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace 는 1 또는 11로 계산할 수 있으며, King, Queen, Jack 은 각각 10으로 계산한다.
##### • 게임을 시작하면 플레이어는 두 장의 카드를 지급받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21 에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅금액을 모두 잃게된다.
##### • 처음 두 장의 카드합이 21일 경우, 블랙잭이 되면 베팅금액의 1.5배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우, 플레이어는 베팅한 금액을 돌려받는다.
##### • 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아있던 플레이어들은 가지고 있는 패에 상관없이 승리해 베팅 금액을 받는다.

## 프로그래밍 요구사항 - 객체
```
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
##### • 다음 Card 객체를 활용해 구현해야 한다. 
##### • Card 기본생성자를 추가할 수 없다. 
##### • 필드(인스턴스변수)인 symbol 과 type 의 접근제어자 private 을 변경할수없다. 
##### • Card 에 필드(인스턴스변수)를 추가할 수 없다. 
```
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
##### • 다음 Player 객체를 활용해 구현해야 한다. 
##### • Player 기본생성자를 추가할 수 없다. 
##### • 필드(인스턴스변수)인 name, bettingMoney, cards 의 접근제어자 private 을 변경할 수 없다. 
##### • Card 에 필드(인스턴스변수)를 추가할 수 없다.
```
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
##### • 다음 Dealer 객체를 활용해 구현해야 한다. 
##### • Dealer 기본생성자 이외 다른 생성자를 추가할 수 없다. 
##### • 필드(인스턴스변수)인 cards 의 접근제어자 private 을 변경할 수 없다. 
##### • Dealer 에 필드(인스턴스변수)를 추가할 수 없다.

## 프로그래밍 요구사항 (선택, 필수아님)
##### • 기본으로 제공하는 Card, Player, Dealer 객체에 예외처리가 되어 있지 않다. 예외처리를 한다. 
##### • Player 와 Dealer 를 구현하다보면 중복코드가 발생할 수 있다. 중복코드를 제거해본다. 
##### • 힌트 : 자바의 상속을 활용해 중복을 제거해본다.

## 프로그래밍 요구사항 - 1,2주차와 동일
##### • 자바 코드 컨벤션을 지키면서 프로그래밍한다. 
##### • https://naver.github.io/hackday-conventions-java/ : 좀 더 개선된 컨벤션 참고 문서 
##### • 3항연산자를 쓰지 않는다. 
##### • else 예약어를 쓰지 않는다. 
##### • 힌트 : if 조건절에서 값을 return 하는 방식으로 구현하면 else 를 사용하지 않아도 된다. 
##### • else 를 쓰지말라고 하니 switch/case 로 구현하는 경우가 있는데, switch/case 도 허용하지 않는다. 

## 프로그래밍 요구사항 - 3주차 변경 및 추가
##### • 함수(또는메소드)의 길이가 10라인을 넘어가지 않도록 구현한다. 
##### • 최대한 10라인을 넘지않기위해 노력하고, 정말 힘든경우 15라인까지 허용한다. 
##### • 함수(또는메소드)가 한 가지 일만 잘하도록 구현한다. 
##### • indent(인덴트,들여쓰기) depth 를 2가 넘지 않도록 구현한다. 1까지만 허용한다. 
##### • 최대한 1을 유지하기 위해 노력하고, 정말 힘든경우 2까지 허용한다. 
##### • 예를들어, while 문 안에 if 문이 있으면 들여쓰기는 2이다. 
##### • 함수(또는메소드)의 인자 수를 3개까지만 허용한다. 4개 이상은 허용하지 않는다.

# 기능 구현 목록 작성

## Card Class

## Player Class
##### - 스캐너로 이름을 입력받으면, 쉼표(,) 단위로 split 해서 이름을 지정해주는 기능
##### - 가지고 있는 카드의 총 숫자 합을 출력해주는 기능
##### - 가진 카드를 모두 출력해주는 기능
##### - 가진 카드의 총 숫자 합이 21과 같은지 확인해주는 기능
##### - 가진 카드의 총 숫자 합이 21보다 큰지 작은지 확인해주는 기능
##### - 최종 수익 출력

## Dealer Class
##### - 가지고 있는 카드의 총 숫자 합을 출력해주는 기능
##### - 가진 카드중 하나를 출력해주는 기능
##### - 가진 카드 목록을 출력해주는 기능
##### - 가진 카드의 총 숫자 합이 21과 같은지 확인해주는 기능
##### - 가진 카드의 총 숫자 합이 21보다 큰지 확인해주는 기능
##### - 가진 카드의 총 숫자 합이 16보다 큰지 작은지 확인해주는 기능
##### - 최종 수익 출력

## InputScanner Class
##### - Scanner로 입력받아야 하는 상황이 생길 때, 호출할 수 있도록 한다.

## OutputPRint Class
##### - 출력문구가 필요한 상황이 생길 때, 출력할 수 있도록 한다.