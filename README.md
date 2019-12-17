# 블랙잭 게임

### 구현할 기능 목록

1. 참가자 입력
- 사용자로부터 이름을 입력받는다.
    - ,를 기준으로 문자열을 분리해 게임에 참여한 참가자 entry를 생성한다.
    - 예외 : 문자열이 , 로 시작하거나 끝날 경우 → 이름이 null인 참가자를 제거한다.
    - 예외 : 문자열 중간에서 , ,처럼 콤마가 연속될 경우 → 이름이 null인 참가자를 제거한다.

2. 배팅 금액 입력
    - 모든 참가자의 배팅 금액을 입력받는다.
    - 예외 : 0, 음수, 소수가 입력되었을 때 → 재입력을 받는다.
    - 숫자가 아닌 문자가 입력되었을 때 → 재입력을 받는다.

3. 게임 진행
    1. 카드 분배
        1. 딜러와 참가자에게 카드를 배분한다.
        2. 딜러의 카드 한장을 출력한다.
        3. 참가자의 카드 패를 출력한다.            
    2. 플레이어 별 진행
        1. 플레이어의 카드패를 출력한다.
        2. (21 이하일 때) 카드를 추가로 받을지 묻는다.
            1. (카드 추가) ii-a부터 반복한다.
            2. (카드 추가 X) 턴을 종료한다.
            3. 예와 - y, n 이외의 입력일 때 → 재입력을 받는다.
        5. (21 초과일 때) 21 초과 사실을 알리고 턴을 종료한다.
        6. (21일 때) 21 완성 사실을 출력하고 턴을 종료한다.
    3. 딜러 진행
        1. (16 이하일 때) 카드 한장을 추가로 받는다.
            1. (21 초과시) 카드 한장을 추가로 받는다.
            2. (16 이하일 때) iii-a부터 반복한다.
        2. (17 이상일 때) 턴을 종료한다.
4. 게임 종료
    - 딜러와 모든 참가자의 패와 점수를 출력한다.
    - 정산
        - (처음 두장이 21일 경우) 배팅 금액의 1.5배를 받는다.
        - (딜러와 점수가 동일할 경우) 무승부로 배팅 금액을 그대로 유지한다.
        - (딜러보다 점수가 큰 경우) 배팅 금액의 2배를 받는다.
        - (딜러보다 점수가 작은 경우) 배팅 금액의 2배를 잃는다.
    - 딜러와 모든 참가자의 최종 수익을 출력한다.