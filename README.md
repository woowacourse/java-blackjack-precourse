# 블랙잭 게임
## 구현할 기능 목록
1. 게임에 참여할 사람의 이름을 입력 받는다.
    - 쉼표 기준으로 분리한다.
    - 입력의 유효성을 검사한다.
        - 입력이 있는지 확인
        - 이름의 글자 수가 최대 글자 수 이내인지 확인
        - 공백이 있는지 확인

2. 참여자들의 배팅 금액을 입력 받는다.
    - 입력의 유효성을 검사한다.
        - 입력이 있는지 확인
        - 양수인지 확인

3. 참여자와 배팅 금액으로 Player 객체를 생성한다.

4. 딜러와 참여자에게 랜덤하게 카드를 2장씩 나누어 준다.
    
5. 참여자에게 블랙잭이 있는지 확인을 한다.
    - 있다면 딜러가 블랙잭인지 확인한다.
        - 딜러가 블랙잭이 아니라면 딜러는 참여자에게 배팅액의 1.5배를 지급한다.
        - 딜러가 블랙잭이라면 참여자는 배팅액만 돌려받는다.

6. 참여자가 한장의 카드를 더 받을지 입력받는다.
    - 입력의 유효성을 검사한다.
        - y,n 둘 중 하나 인지 확인

7. 딜러의 카드가 16이하라면 한장을 더 받는다.
    - 딜러의 패가 21을 초과하게 되면, 그 시점까지 남아있는 참여자는 모두 배팅 금액을 받는다.

8. 최종 결과를 출력한다.
    - 참여자와 딜러의 패를 출력한다.
    - 각자의 패의 합을 결과값으로 출력한다.
    - 결과값에 따라 딜러와의 승패를 확인한다.
    - 승패에 따라 배팅 금액을 지급한다.
    
9. 최종 수익을 출력한다.

## 요구사항
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
- 함수의 길이가 10라인을 넘어가지 않도록 구현한다.
- else 예약어 (switch/case)를 사용하지 않는다.
- indent depth를 2이 넘지 않도록 구현한다. 1까지만 허용한다.
- 함수의 인자 수를 3개까지만 허용한다. 4개 이상은 허용하지 않는다.
- 3항 연산자를 쓰지 않는다.
- 불필요한 공백 라인을 쓰지 않는다.
- Player와 Dealer의 중복 코드를 제거해 본다. (상속 이용)
- Card, Player, Dealer 객체의 예외 처리를 한다.