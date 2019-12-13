# 블랙잭 게임 구현

#### 게임룰

- 딜러와 플레이어들이 있음

  - 플레이어는 쉼표 기준으로 분리하여 이름을 입력받음
  
  > 이름은 알파벳으로만 입력을 받으려 함
  > 
  > 알파벳이 아니거나 아무것도 입력받지 않으면, 다시 입력하라고 할 것
  >
  > 중복된 이름의 경우 임의로 뒤에 -n을 붙여줄 것. (name-1, name-2) - 동명이인이 있을 수 있으니까.

  - 플레이어가 딜러를 이기면 됨. 플레이어끼리 경쟁하지 않음

- 딜러와 플레이어 중 카드의 합이 21이거나, 21에 더 가까운 숫자를 가지는 쪽이 이김

- Betting

  - 카드를 받기 전, 플레이어가 베팅할 금액을 각자 원하는 만큼 지정
  
  > 배팅 금액은 INT 범위 내의 0이상 숫자로만 입력받음, 아닐 경우 다시 입력받기
  >
  > 한국 돈을 기준으로 생각하여, 10원 단위까지 허용할 예정, 아닐 경우 다시 입력받기
  
- The Deal

  - 모두 2장씩 카드를 받음 (중복 X)
  
  - 기본 룰과 다르게, 해당 프로그램에선 나눈 카드를 전체 OPEN

- BLACKJACK

  - 처음 두 장의 카드 합이 21일 경우
  
  - 플레이어만 블랙잭인 경우 > 베팅 금액의 1.5배를 딜러에게 받음
  
  - 플레이어와 딜러 모두 블랙잭인 경우 > 플레이어가 베팅한 금액을 돌려받음 & 게임 종료
  
  - 딜러가 블랙잭인 경우 > 플레이어가 베팅한 금액을 돌려주지 않음 & 게임 종료

- 카드 추가

  - 플레이어가 1명 이상 남아있을 때,

  - 딜러 : 처음에만 한 장 추가될 수도 있음 - 받은 2장의 합계가 16이하이면 추가됨
  
  > 딜러의 카드 합이 21이 넘는다면, 딜러 패배. 살아있는 플레이어들은 모두 승리.
  
  - 플레이어 : 20 이하일 때, 원한다면 카드 추가 가능
  
- 승패결정

  - Win : 딜러보다 21에 가까움. 베팅한 금액만큼 딜러에게 받음
  
  - Push : 딜러와 동일함. 베팅한 금액을 돌려받음
  
  - Lose : 딜러가 21에 더 가까움. 베팅 금액을 딜러에게 줌.
  
<br>

#### 카드

- 카드의 숫자 계산은 카드 숫자를 기본으로 함

- Ace는 1 또는 11로 계산 가능

- King, Queen, Jack은 각각 10으로 계산

- 카드 셋 (52장)

  - Ace♠, 1♠, 2♠, ..., 9♠, King♠, Queen♠, Jack♠, Ace◆, ..., Jack◆, Ace♣, ..., Jack♥

<br>

#### 플레이어

- 게임을 시작할 때 배팅 금액을 정함

- 게임 시작시 두 장의 카드를 지급받음

  - 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이김

  - 21을 넘지 않을 경우 원한다면 얼마든지 카드를 뽑을 수 있음

- 단 카드가 21을 초과할 경우 배팅 금액을 모두 잃게 됨

<br>

#### 딜러

- 처음엔 돈이 없음

- 게임 시작시 두 장의 카드를 지급받음

  - 합이 16이하이면 반드시 1장의 카드를 추가로 받아야 함

  - 합이 17점 이상이면 추가로 받을 수 없음
