# 블랙잭 게임 구현

#### 게임룰

- 딜러와 플레이어가 있음

- 딜러와 플레이어 중 카드의 합이 21이거나, 21에 더 가까운 숫자를 가지는 쪽이 이김

- 합이 21일 경우엔 블랙잭이라 함

<br>

#### 카드

- 카드의 숫자 계산은 카드 숫자를 기본으로 함

- Ace는 1 또는 11로 계산 가능

- King, Queen, Jack은 각각 10으로 계산

<br>

#### 플레이어

- 게임을 시작할 때 배팅 금액을 정함

- 게임 시작시 두 장의 카드를 지급

  - 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이김

  - 21을 넘지 않을 경우 원한다면 얼마든지 카드를 뽑을 수 있음

- 단 카드를 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 됨

- 첫 두장의 카드 합이 블랙잭

  - 배팅 금액의 1.5배를 딜러에게 받음
  
  - 단, 딜러도 블랙잭이면 베팅 금액만 돌려받음

- 딜러와 플레이어가 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받음

<br>

#### 딜러

- 게임 시작시 두 장의 카드를 지급

  - 합이 16이하이면 반드시 1장의 카드를 추가로 받아야 함

  - 합이 17점 이상이면 추가로 받을 수 없음

- 합이 21을 초과하면, 패배

  - 그 시점까지 남아있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받을 수 있음
