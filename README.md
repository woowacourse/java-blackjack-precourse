## 블랙잭 게임

---

### 구현할 기능 목록

> #### 게임 진행

- 기본적으로 카드의 점수 합이 21에 가까운 숫자를 가지는 쪽이 이긴다
- 이름을 입력하여 참가한다
- 순서대로 배팅금액을 입력한다
- 시작 시 딜러와 플레이어들은 두장의 카드를 뽑는다
  - 딜러는 한 장을 공개하고 플레이어 들은 2장을 공개한다
  - 플레이어는 처음 두장의 점수 합이 딱 21이면 배팅 금액의 1.5배를 딜러에게 받는다
- 플레이어 들은 순서대로 뽑고 싶은 만큼 카드를 뽑는다
  - 뽑을 때 마다 자신의 카드를 전부 공개한다
  - 점수 합이 21을 초과하지 않으면 계속 뽑을 수 있다
  - 더 뽑았는데 21을 넘으면 배팅 금액을 잃는다
- 딜러는 처음 두장의 점수에 따라 행동이 강제된다

  - 딜러는 2장의 합계가 16이하이면 반드시 1장을 추가로 받는다
  - 2장의 합계가 17이상이면 추가로 받을 수 없다
  - 딜러가 21점을 초과하면 그 시점에 남아있는 플레이어들은 전부 승리해 배팅금액을 받는다

> #### 참가자

- 자신이 가진 카드들의 점수 합을 계산 한다
- 카드를 뽑는다
- [딜러]
- [플레이어]
  - 6명으로 제한한다 (제주 신라호텔 카지노 룰 참고)
  - 이름
    - 비어 있지 않은 이름을 받는다
  - 배팅 금액
    - 1원 이상을 배팅한다

> #### 카드 뭉치

- 온전한 카드 뭉치로 시작해야 한다
- 랜덤한 카드를 뱉는다
  - 뽑힌 카드는 카드 뭉치에서 제거되어야 한다
  - 덱을 전부 뽑을 일 없도록 인원을 제한 했다(6명)
- 점수는 기본적으로 카드의 숫자를 따라간다
  - Ace는 1또는 11로 할 수 있다
  - J,Q,K은 10으로 한다

> #### 입력

- 플레이어 이름을 입력받는다
  - 참가자의 이름 제한 조건을 따른다
- 배팅할 금액을 입력받는다
  - 참가자의 배팅금액 제한 조건을 따른다