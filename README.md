# 블랙잭 게임
## 기능 구현 목록

*  (완료) 사용자가 입력한 이름을 쉼표 기준으로 분리한다.
*  (완료) 플레이어별로 배팅 금액을 입력받고, Player 객체를 생성한다.
*  (완료) 플레이어들의 배팅 금액을 딜러가 가져간다.
*  (완료) 딜러와 플레이어들에게 카드를 중복 없이 2장씩 할당한다.
    * (완료) 나누어진 카드를 출력한다.
    * (완료) 이 때 플레이어가 가진 2장의 합이 21이면 딜러에게 배팅 금액의 1.5배 받는다.
    * (완료) 이 때 플레이어와 딜러 카드 모두 21이면 플레이어는 배팅 금액을 돌려받는다.
*   (완료) 플레이어별로 한 장의 카드를 더 받을지 물어본다.
    *   예외 - 플레이어가 y, n 이외의 값을 선택할 경우
    *   플레이어 카드의 합이 21 이상일 경우 자동으로 넘어간다.
*   (완료) 딜러가 카드를 받을지 결정한다.
*   (완료) ACE 카드가 있다면 카드 합을 +10 해도 21이 넘지 않는지 확인
*   (완료) 우승자를 정한다.
*   (완료) 딜러와 우승자가 배팅금을 나눈다.