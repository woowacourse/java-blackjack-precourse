- 2019.12.11
- 우아한테크코스 프리코스 3주차
- 블랙잭
# 블랙잭
- 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 
  숫자를 가지는 쪽이 이기는 게임이다.
- 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 
  예외로 Ace는 1 또는 11로 계산할 수 있으며, 
  King, Queen, Jack은 각각 10으로 계산한다.

- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 
  두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 
- 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 
- 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 
- 딜러와 플레이어가 모두 동시 에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.

- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 
  17점 이상이면 추가로 받을 수 없다. 
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 
  가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.



# 기능 목록 및 예외
|구현여부| 기능           | 예외처리 |
|----- |---            |---   |
|      | 참여자 이름 입력  | - 0명 입력했을 경우|
|      |               | - 특수문자가 포함되었을 경우|
|      | 배팅금액 입력     | - 0 혹은 음수 입력 |
|      | 게임시작 (카드분출) |  |
|      | 게임 상태 출력 |  |
|      | 카드 추가 여부 확인 | - y,n 이외의 입력 |
|      | 딜러카드 추가 확인 |  |
|      | 점수 계산 및 출력 |  |
|      | 최종 수익 출력 | |
