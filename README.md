# 블랙잭 게임
## 게임 소개
*   딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
*   플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
*   카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
*   게임을 시작하면 플레이어는 2장의 카드를 지급받는다. 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
*   플레이어는 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
*   처음 두 장의 카드 합이 21일 경우 블랙잭이 되며, 배팅 금액의 1.5배를 딜러에게 받는다. 딜러와 플레이어가 동시에 블랙잭인 경우 플레이어는 배팅한 금액을 돌려받는다.
*   딜러는 처음에 받은 2장의 합계가 16 이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
*   딜러가 21을 초과하면 그 시점까지 남아있던 플레이어들은 가지고 있던 패에 상관 없이 승리해 배팅 금액을 받는다.

## 기능 구현 목록
*  (완료) 사용자가 입력한 이름을 쉼표 기준으로 분리한다.
    *   (완료) 예외 - 입력한 이름이 유효한지 확인한다.
*  (완료) 플레이어별로 배팅 금액을 입력받고, Player 객체를 생성한다.
    * (완료) 예외 - 배팅 금액이 0 이하인 경우를 처리한다.
*  (완료) 플레이어들의 배팅 금액을 딜러가 가져간다.
*  (완료) 딜러와 플레이어들에게 카드를 중복 없이 2장씩 할당한다.
    * (완료) 나누어진 카드를 출력한다.
    * (완료) 이 때 플레이어가 가진 2장의 합이 21이면 딜러에게 배팅 금액의 1.5배 받는다.
    * (완료) 이 때 플레이어와 딜러 카드 모두 21이면 플레이어는 배팅 금액을 돌려받는다.
*   (완료) 플레이어별로 한 장의 카드를 더 받을지 물어본다.
    *   (완료) 예외 - 플레이어가 y, n 이외의 값을 선택할 경우
    *   (완료) 플레이어 카드의 합이 21 이상일 경우 자동으로 넘어간다.
*   (완료) 딜러가 카드를 받을지 결정한다.
*   (완료) ACE 카드가 있다면 카드 합을 +10 해도 21이 넘지 않는지 확인
*   (완료) 우승자를 정한다.
*   (완료) 딜러와 우승자가 배팅금을 나눈다.
*   (완료) 출력 시 1, 11 -> ACE 로, 10-> J or Q or K 로 변환.
*   (완료) 출력 시 SPADE -> 스페이드 로 변환.