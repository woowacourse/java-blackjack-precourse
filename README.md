#프리코스 3주차 - 블랙잭 게임
##기능목록
* 사용자의 이름을 입력받는 기능 구현
    
    1. 출력 
    
    2. 예외처리
        * 아무것도 입력하지 않는 경우
        * 이름의 길이가 0이거나 6이상인 경우
      
        
* 사용자의 배팅 금액을 입력받는 기능 구현
    
    1. 출력
    
    2. 예외처리
        * 아무것도 입력하지 않는 경우
        * 음수인 경우

* 최초 2장의 카드를 나눠주는 기능 구현
    
    1. 출력 
        
        * Card 출력 방식
        
            Card 출력 방식을 예제와 같이 나타내기 위해 Symbol 과 Type 에 한글 이름을 붙여주기로 하였다.
            Enum type 인 SYMBOL 과 TYPE
             에 대한 수정에 제한이 명시되어있지 않아 [우아한 형제들 기술 블로그](http://woowabros.github.io/tools/2017/07/10/java-enum-uses.html) 를 참고하여 수정하였다.  
    2. 기능

* 플레이어 추가 draw 구현
* 딜러 추가 draw 구현
* 게임 결과 출력 구현
* 게임 결과 게산 구현
    
    * ACE 처리 기능
    * 첫 두장이 BLACKJACK 인 경우
    
        1. 딜러와 플레이어 모두 BLACKJACK
        2. 딜러만 BLACKJACK
        3. 플레이어만 BLACKJACK
    
    * 플레이어 
        1. BLACKJACK 초과
        2. BLACKJACK
        3. BLACKJACK 미만
    * 딜러 
        1. BLACKJACK 초과
        2. BLACKJACK
        3. BLACKJACK 미만
    