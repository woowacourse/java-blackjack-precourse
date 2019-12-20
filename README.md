### 서론

- 모든 프리코스를 마친 기념으로 [회고](https://takhyeongmin.github.io/2019/12/17/WTCPrecourseRemind/) 를 작성하였다.
- 내 경험을 모두에게 공유하여 이 프로젝트를 모두가 구현해봤으면 좋겠다.



### 기능 리스트

- [x] 기능 : 초기 출력
- [x] 기능 : 플레이어이름 입력받기
- [x] 기능 : 배팅금액 입력받기
- [x] 기능 : 플레이어 생성하기
- [x] 기능 : 랜덤 카드 생성
- [x] 기능: 플레이어 초기 카드 받기
  
- [x] 기능 : 받은 카드 출력하기
- [x] 기능 : 에이스를 포함하고 있는지 판단 - 계산할 때 사용해야함
- [x] 기능 : 숫자가 21을 초과했는지 판단
- [x] 기능 : 첫 패가 블랙잭인지 판단 - 딜러, 플레이어
  
- [x] 기능 : 딜러에게 ACE를 1로 사용할 것인지 11로 사용할 것인지 묻기
- [x] 기능 : 16이하인지 판단 - 딜러
- [x] 기능 : 16 이하일 때 딜러 하나 더 뽑기
- [x] 기능 : 사용자에게 카드를 더받을 것인지 묻기
- [x] 기능 : 사용자의 카드 더받기 입력 받기
- [x] 기능 : 사용자의 카드 더받기 입력에 따른 후속 동작
- [x] 기능: 결과를 계산하는 함수
- [x] 기능: 승패 계산하기
  
- [x] 기능: 딜러 금액 계산하기
- [x] 기능: 초기 게임 환경 설정하기
- [x] 기능: 카드 더받기 과정 흐름 구현하기
- [x] 기능: 첫 패가 블랙잭인 경우 결과 계산
- [x] 기능:딜러가 21을 넘었을 경우
- [x] 기능: 딜러 카드 더받기 과정 흐름 구현하기
- [x] 기능: 뽑힌 카드들 모든 카드에서 제거
- [x] 기능: 최종 결과 출력
- [x] 버그 해결: 최종 스코어 결과 출력문 오류
- [x] 버그 해결: 딜러의 카드받기 메세지 구현 추가
- [x] 버그 해결: 최종 수익 결과 노출 오류
- [x] 버그 해결: Ace의 BonusScore를 계산하지 못하는 오류 해결
- [x] 버그 해결: 이겼을 경우 수익 출력 오류
- [x] 버그 해결: 중복 Ace Score 계산 오류
- [x] 버그 해결: 딜러는 버스트되지 않는이상 Ace를 11로 계산 적용
- [x] 버그 해결: 블랙잭을 체크하는 기능 이상
- [x] 예외: 잘못된 배팅 금액 입력
- [x] 예외: 이름 입력시 콤마의 수와 이름의 수가 다를 경우
- [x] 예외 : 카드의 생성이 이루어지지 않았을 경우
- [x] 예외: 플레이어 이름에 `,`, `,` 만 입력되었을 경우



### 작업 일지

- 12일 

  - [객체 지향 언어의 이해: Java가 메모리를 활용하는 방법](https://takhyeongmin.github.io/2019/12/12/oop1/)
  - [객체 지향 언어의 이해: 추상화와 메모리](https://takhyeongmin.github.io/2019/12/12/oop2abstractAndMemory/)
  - [객체 지향 언어의 이해: 객체 설계 원칙 : SOLID](https://takhyeongmin.github.io/2019/12/12/oop3SOLID/)
    - 어떻게하면 클래스를 잘 나눌 수 있을까를 고민하며 닥치는대로 자료들을 뒤져보고 블로그에 정리해보았다.
- 13일

  - [Java: Stream API 정리](https://takhyeongmin.github.io/2019/12/13/WhatIsStream/)
    - Depth 1 요구사항을 효율적으로 해결하기 위해 Stream 정리 후 블로그 포스팅
  - [Java 성능 좋은 분기문, if문에서 연산 순서](https://takhyeongmin.github.io/2019/12/13/HowToGoodIf/)
  - [Anti-OOP : if문을 피하자!](https://takhyeongmin.github.io/2019/12/13/AntiOOP/)
    - `else` 키워드 제거와 효율적인 `if` 문을 활용하기 위한 학습
- 14일
- [Java Enum 활용기](https://takhyeongmin.github.io/2019/12/14/HowToJavaEnumClass/)
    - `else` 키워드 제거를 위한 방법들을 찾던 도중 `Enum` 활용법이 너무 잘 설명돼있어서 겸사겸사 학습
- 15일
  - 초기 기능 리스트 작성
    - 기능 리스트 작성에 대한 예를 보고 기능 리스트를 더욱 세분화하려고 시도하였다.
    - 그러자, 함수 단위로 기능 목록이 작성되어 1주차 피드백 **기능 목록 구현을 재검토한다.** 의 항목을 위반하는 것을 발견...!
    - 조금 더 큰 단위로 분할하여 기능 리스트를 다시 작성하였다.
  - 프로젝트 구조 작성
    - 상속을 활용할 수 있는 방법을 구상하였다.
    - 비즈니스 로직과 ui로직 분리를 고민하였다...!
    - 결국 View를 담당하는 OutputUtil에 값들을 넘겨 출력하는 쪽으로 결정하였다.
    - 일급 컬렉션으로 기본 타입들을 변환하여 작성하여 관련 로직의 책임을 넘기려 시도했다.
- 16일
  - 프로젝트 구현
    - 블랙잭 룰을 잘몰라 여러 차례 재작성하였다....!
- 17일
  - 예외 처리 및 프로젝트 구조 리팩토링



### 기능 요구사항

- 블랙잭 게임을 진행하는 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가 까운 숫자를 가지는 쪽이 이기는 게임이다.

- 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.

- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가 깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.

- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시 에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.

- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받 는다.



### ### 3주차 회고



- 매 주마다 이렇게 부족한 점이 많은지 모르겠다...!
  - 하나씩 궁금증을 해소해 나감에도 불구하고 돌아보면 잘못된 구현같다...!
  - 그러나, 아주 아주 아주 도움이 됐던 건 피드백이다.
  - 코드에 대한 피드백이 있음에 감사하다...!
  - ~~물론 내가 실력이 부족해서 많은 사람에게 적용되는 피드백 대부분이 나에게 좋은 조언이 되기 떄문이기도 하다...~~
- 이번 프로젝트에서도 많은 부분에서 도움을 받았다.
- 비즈니스 로직과 UI로직을 분리하는 것은 아주 기초적인 내용임에도 놓치고 있었다.
  - 아무리 가벼운 토이 프로젝트에서도 이 원칙을 지키려고 노력해야겠다.
- 내가 느끼기엔 극단적인 요구 사항이 많았다.
  - 당연한 걸 극단적으로 느낄만큼 여지껏 가독성에 아주 해로운 아주 편리한 코드작성만을 해왔던 나를 반성한다.
  - 그렇지만 내가 느끼기엔 극단적인 요구 사항을 지키려 노력하며 깨달은 게 많다.
  - 대부분의 경우에서 함수, 클래스의 단일 책임 원칙이 자연스럽게 지켜진 점,
  - 이름 명명에 많은 시간을 투자하는 것이다.
  - 또한 depth가 1로 줄어들어 java api에 대한 이해도가 높아지고, 함수를 잘게 잘게 나누는 경험도 아주 도움이 됐다.
- 아무렇게나 작성했던 접근지정자와 final을 적절히 이용해 side effect를 제거하려고 노력하기도 했다.
- 이렇게 프리코스를 모두 경험하며 얻은 키워드는 참 많다.
- `SOLID`, `SRP`,  `기능 단위 commit`, `enum`, `else keyword의 단점`, `일급 컬렉션`, `java stream` 등 너무 많은 부분에서 도움이 많이 되었다...!
- 코드에 대한 피드백이 한번 더 도착한다면 이 문서를 업데이트 하겠다.
- ~~제발제발제발제발제발제발제발 오프라인 코테를 성공적으로 끝내 입과할 수 있으면 좋겠다....!~~



### 3주차 피드백



>  *기울어진 글씨* 는 나의 첨언이다.



- 시작 단계에서 너무 완벽한 설계하는 것을 경계하라.
  - 시작 단계는 요구사항에 대한 배경 지식(보통 **도메인 지식**이라고 부른다.)이 부족한 상태이다.
  - 따라서 시작 단계에 좋은 설계를 하기 힘들다. 아니 거의 불가능하다.
  - 시작 단계에 완벽한 설계를 하겠다는 욕심을 버려라.
  - 시작 단계에서 **분석 가능한 상태로 설계 진행, 구현하면서 배경 지식을 쌓아간다.**
  - 배경 지식이 쌓였다 판단된느 시점에 재설계하고 구현한다.
  - 이 과정을 **반복**해 나간다.
  - *우선 몸으로 부딪히고 수정해나가라는 조언*



- 상황(Context)에 맞는 설계와 구현 방법을 찾아라.
  - 프로그래밍 설계와 구현은 아날로그적인 영역이 많은 부분이다.
  - 프로그래밍을 기술이 아닌 예술의 일부라고 생각하는 이유도 이런 점 때문이다.
  - **프로그래밍 설계와 구현에 정답은 없다.**
  - 정답을 찾기보다 **요구사항에 적합한 최선의 설계와 구현 코드를 찾기 위해 노력** 한다.
  - *위에서 말한 노력도 노력이지만 적합한 최선의 설계와 구현 코드를 찾기 위해선 그에 걸맞는 배경 지식들이 필요하다. 나에겐 배경지식을 쌓는 것에도 절대 소홀하지 말자.*



- 원시 타입과 문자열을 포장하라.

  - 각 카드 숫자의 합을 Score 객체로 포장

    - ```java
      public class Score {
        private static final int SCORE_MIN = 0;
        private static final int BLACKJACK_SCORE = 21;
        private static final int TEN = 10;
        
        public static final Score Zero = new Score(0);
        
        private final int score;
        
        public Score(int score) {
          if (score < SCORE_MIIN) {
            throw new IllegalArgumentExcepttion("점수는 0이상이어야 합니다.")
          }
          this.score = score;
        }
        ...
      }
      ```

  - y 또는 n 값을 YesOrNo 객체로 포장

    - ```java
      public class YesOrNo {
        private final char value;
        
        public YesOrNo(String value) {
          if (Objects.isNull(value)) {
            throw new IllegalArgumentException("값이 null이 될 수 없습니다.")
          }
          if(value.length() > 1) {
            throw new IllegalArgumentException("입력 문자가 두 자 이상이 될 수 없습니다.")
          }
          
          this.value = Character.toLowerCase(value.charAt(0));
          if(this.value != 'y' && this.value != 'n') {
            throw new IllegalAArgumentException("입력 문자는 y(Y), n(N)만 가능합니다.");
          }
        }
        
        public boolean isYes() {
          return value == 'y';
        }
      }
      ```

  - **프로그래밍 설계와 구현에 정답은 없다.** 에 기초해 앞의 YesOrNo는 enum 또는 boolean 객체를 포장하는 구조로 개발할 수도 있다.



- **적절한 Collection(자료구조)를 활용하라.**
  - 일차로 List,Set,Map을 적절하게 활용한다.
  - 적절한 자료구조가 없다면 나만의 자료구조를 구현한다.
    - 나만의 클래스를 추가하는 것 == 나만의 자료구조



- 일급 Collection(자료구조)을 사용한다.

  - [일급 컬렉션의 이점](https://jojoldu.tistory.com/412) 을 읽어보지 않은 사람은 읽어보길 권장한다.

  - Collection을 객체로 포장한다.

  - 일급 Collection은 Collection 이외에 다른 필드가 없어야한다.

    - *여러 자료들을 참고하니 상수는 해당되지 않는 듯하다.*
    - *애당초 **필드**는 클래스 안에서 선언되는 멤버 변수이다.*

  - ```java
    publi class Cards {
      private final List<Card> cards;
      
      public Cards(List<Card> cards) {
        this.cards = cards;
      }
      
      private Score reviseAceScore(Score score) {
        if (!hasAce(cards)) {
          return score;
        }
        return score.plusTenIfNotBust();
      }
      
      private Score calculateRawScore() {
        Score score = Score.ZERO;
        for (Card card : cards) {
          score = card.caluculate(score);
        }
        return score;
      }
      
      private static boolean hasAce(List<Card) original) {
        return original.stream().filter(Card::isAce).findFirst().isPresent();
      }
    }
    ```

    

- 객체에 메시지를 보내라

  - 상태 데이터를 가지는 객체에서 데이터를 꺼내려(get) 하지 말고 객체에 메시지를 보내라.

  - **카드 점수를 구하는 로직 구현**

    - 카드 점수를 구하는 로직을 Score 객체로 추상홯나다고 가정할 경우 Score가 점수 계산 로직과 관련한 모든 책임을 지도록 한다.

    - ```java
      public class Score {
        private static final int SCORE_MIN = 0;
        private static final int BLACKJACK_SCORE = 21;
        private static final int TEN = 10;
        
        public static final Score ZERO = new Score(SCORE_MIN);
        
        private final int score;
        
        ...
          
        public Score calculate(int score) {
          return new Score(this.score + score);
        }
        
        public Score plusTenIfNotBust() {
          Score score = new Score(this.score + TEN);
          if(score.isBust()) {
            return this;
          }
          return score;
        }
        
        private boolean isBust() {
          return this.score > BLACKJACK_SCORE;
        }
      }
      ```

    - 나는 이런 로직이 사용되지 않는 방식을 사용하였는데 적용해서 리팩토링을 시도해봐야겠다.

  - **카드가 Ace인지 여부를 판단하는 로직**

    - enum도 자바 객체와 같다. enum도 값을 꺼내지(get) 말고 메시지를 보내 로직을 구현한다.

    - ```java
      public enum Symbol {
        ACE(1),
        TWO(2),
        THREE(3),
        ...
          
      	private int score;
        
        Symbol (int score) {
          this.score = score;
        }
        
        public int getScore() {
          return score;
        }
        
        public boolean isAce() {
          return this == ACE;
        }
      }
      ```

    - 위의 isAce로직을 난 밑의 코드로 구현하였다.

    - ```java
      public int getAceCount() {
              return (int) cards.stream().filter(card ->
                      card.getSymbol() == Symbol.ACE
              ).count();
          }
      ```

    - 객체에 메세지를 보내라는 것에 실수하지 않으려고 인식하고 코드를 작성하였음에도 불구하고 또 `getSymbol()` 를 꺼내고 ACE인지 판별하고 있었다.

    - ~~심지어 리팩토링을 한다고 했는데도 인지하지 못했다...! 피드백을 두번은 봐야 정신차리는군...!~~

    - 반성하고 같은 실수를 더는 반복하지말자.

