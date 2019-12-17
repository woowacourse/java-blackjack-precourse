## 프리코스 3주차 BlackJack

| 작성자 | version |git ID| 작성일시 | 변경사유 |
|---|---|---|---|---|
| 정은석 | 1.0v |joseph415 |2019.12.17 | ConstructionPlayerAndDearler 이름 변경,view를 담당하는 class생성 |

___

##### 기능 요구 사항
* 블랙잭 게임을 진행하는 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가 까운 숫자를 가지는 쪽이 이기는 게임이다.

* 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며,
예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.

* 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가 깝게 만들면 이긴다. 
21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.

* 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 
딜러와 플레이어가 모두 동시 에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.

* 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 
딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받 는다.
___

##### 프로그램 요구사항
* 함수 메소드 길이가 15라인 넘지 안도록 한다
* else 예약어 쓰지 않는다
* java convention을 따른다
* 함수(또는 메소드)의 길이가 10라인을 넘어가지 않도록 구현한다.
    - 최대한 10라인을 넘지 않기 위해 노력하고, 정말 힘든 경우 15라인까지 허용한다. 
    - 함수(또는 메소드)가 한 가지 일만 잘 하도록 구현한다.
* indent(인덴트, 들여쓰기) depth를 2가 넘지 않도록 구현한다. 1까지만 허용한다.
    - 최대한 1을 유지하기 위해 노력하고, 정말 힘든 경우 2까지 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
* 함수(또는 메소드)의 인자 수를 3개까지만 허용한다. 4개 이상은 허용하지 않는다.

* Card 객체 1
    - 다음 Card 객체를 활용해 구현해야 한다.
    - Card 기본 생성자를 추가할 수 없다.
    - 필드(인스턴스 변수)인 symbol과 type의 접근 제어자 private을
      변경할 수 없다.
    - Card에 필드(인스턴스 변수)를 추가할 수 없다.
* Player 객체 2
    - 다음 Player 객체를 활용해 구현해야 한다.
    - Player 기본 생성자를 추가할 수 없다.
    - 필드(인스턴스 변수)인 name, bettingMoney, cards의 접근 제어자 private을 변경할 수 없다.
    - Card에 필드(인스턴스 변수)를 추가할 수 없다.
* Dealer 객체 3
    - 다음 Dealer 객체를 활용해 구현해야 한다.
    - Dealer 기본 생성자 이외 다른 생성자를 추가할 없다. 필드(인스턴스 변수)인 cards의 접근 제어자 private을 변경할 수    
      없다.
    - Dealer에 필드(인스턴스 변수)를 추가할 수 없다.
* 선택사항
    - 기본으로 제공하는 Card, Player, Dealer 객체에 예외 처리가 되어 있지 않다. 예외 처리를 한다. 
    - Player와 Dealer를 구현하다보면 중복 코드가 발생할 수 있다. 중복 코드를 제거해 본다.
        - 힌트: 자바의 상속을 활용해 중복을 제거해 본다.
       

---

##### 기능 구현 목록
-[x] 플레이어의 이름을 입력하는 기능
    * 예외처리
    - [x] 빈 string,빈 List, 공백과 함께 입력시 재입력
    - [x] 중복이름 입력불가
-[x] 플레이어의 베팅액을 입력하는 기능
    * 예외처리
    - [x] 금액 음수입력시 재입력
    - [x] 문자 입력받을시 재입력 
-[x] 플레이어가 카드를 뽑을지 입력하는 기능
    * 예외처리
    -[x] yes/no 이외의 입력
    
-[x] 플레이어를 생성하는 기능
-[x] 딜러를 생성하는 기능
-[x] 첫 시작때 플레이어에게 카드를 두장 지급하는 기능
-[x] 첫 시작때 딜러가 카드를 두장 받는 기능
-[x] 딜러와 플레이어의 카드를 지급했다는 상태메시지를 나타내는 기능
-[x] 딜러와 플레이어의 카드 상태메시지를 출력하는 기능

-[ ] 딜러가 받은 카드 숫자가 16이하인지 17이상인지 판단하는 기능
-[ ] 딜러에게 카드를 추가 지급하는 기능
-[x] 플레이어마다 카드를 뽑는기능

-[ ] 첫 시작에 블렉잭이 나오는지 판단하는 기능
-[ ] 딜러와 플레이어가 둘다 블랙젝인지 판단하는 기능
-[ ] 시작과 동시 블랙젝이 나올경우 배팅금액을 주는 기능
-[ ] 카드 숫자의 합이 21을 넘는지 판단하는 기능
-[ ] 카드의 숫자를 계산하는 기능
    * 예외처리 
    -[ ] ACE는 1과 11로 계산가능
    -[ ] King, Queen, Jack은 각각 10으로 계산
-[ ] 최종수익을 출력하는 기능
-[ ] 우승자를 뽑는 기능