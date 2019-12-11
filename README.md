## 블랙잭 게임

---

#### 구현할 기능 목록

> #### 일단 규칙 정리

- 딜러와 플레이어 중 카드의 점수 합이 21에 가까운 숫자를 가지는 쪽이 이긴다

- 점수 계산은 기본적으로 카드의 숫자로 한다

  - Ace는 1 또는 11로 할 수 있다
  - K,Q,J는 각각 10으로 한다

- 플레이어는 게임을 시작할때 배팅해야 한다

- 시작 시 플레이어는 두장의 카드를 지급 받는다

  - 두장의 카드 점수를 합쳐 21을 초과하지 않으면 카드를 계속 뽑을 수 있다
  - 더 뽑았는데 21을 초과하면 배팅금액을 모두 잃는다

  - 처음 두장의 카드 합이 21이면 배팅 금액의 1.5배를 딜러에게 받는다
  - 딜러와 플레이어 동시에 21이면 배팅 금액을 돌려받는다

- 딜러도 처음에 두장의 카드를 받는다

  - 딜러는 2장의 합계가 16이하이면 반드시 1장을 추가로 받는다
  - 2장의 합계가 17이상이면 추가로 받을 수 없다
  - 딜러가 21점을 초과하면 그 시점에 남아있는 플레이어들은 전부 승리해 배팅금액을 받는다

> #### 참가자

- 자신이 가진 카드들의 점수 합을 계산 한다
- 카드를 뽑는다
- 손익을 계산한다

- [딜러]
  - 누가 얼마를 배팅했는지 기록한다
  - 플레이어에게 적절한 금액을 준다
  - 처음에 받은 카드 점수 합에 따라 카드를 추가로 뽑는다
- [플레이어]
  - 배팅한다
  - 돈 받을 때 적절한 금액인지 확인한다
  - 뽑고 싶은 만큼 카드를 추가로 뽑는다

> #### 카드 뭉치

- 온전한 카드 뭉치로 시작해야 한다
- 랜덤한 카드를 뱉는다
  - 뽑힌 카드는 카드 뭉치에서 제거되어야 한다