## 게임 규칙

- 카드 숫자의 합이 21에 가까운 사람이 이긴다.
    - 21을 초과하면 버스트가 되며 딜러의 결과에 관계없이 패배한다. (배팅금액을 모두 잃는다.)
- 카드 숫자 계산
    - K,Q,J는 10으로 계산한다.
    - A는 1 또는 11로 계산한다. (자신에게 유리한 방향으로)
    - 나머지는 카드에 적힌 숫자(2~10)로 계산한다.
- 게임 시작 시 카드 두 장을 받고 시작한다.
    - 숫자의 합이 정확히 21인 경우 블랙잭이 되며 승리한다. (배팅금액의 1.5배를 딜러에게 받는다)
    - 플레이어와 딜러가 모두 블랙잭일 경우 플레이어는 배팅금액을 돌려받는다.
    - 플레이어는 숫자의 합이 21이 되지 않은 경우 계속해서 카드를 다시 받을 수 있다.
- 딜러는 처음 받은 카드 2장의 합이 16이하이면 반드시 1장의 카드를 추가로 받는다. 17이상이면 받지 않는다.
    - 딜러가 21을 초과하면 그 시점까지 남아있던 플레이어들은 승리해 배팅금액을 돌려받는다.
- 배당율은 건 금액만큼을 받는다. (1000원을 걸어 이기면 2000원을 받고 지면 건 금액인 1000원을 잃는다.)

## 게임 순서

- 게임에 참여할 플레이어의 이름을 입력한다.
- 플레이어마다 배팅 금액을 정한다.
- 딜러와 플레이어들에게 카드 두 장씩을 지급한다.
    - 딜러는 한 장의 카드만 공개한다
    - 플레이어는 모든 카드를 공개한다.
- 각 플레이어는 카드를 더 받을지 말지 결정한다.
- 플러이어들의 모든 결정이 끝나면 딜러의 카드 수령 여부를 체크한다. (16이하이면 카드 수령)
- 최종 결과를 출력한다.
- 최종 수익을 출력한다.

## 구현할 기능 목록

- 게임에 참여할 플레이어의 이름을 입력한다. (쉼표로 구분)
    - [예외 처리] 아무것도 입력하지 않은 경우
- 플레이어마다 배팅 금액을 정한다.
    - [예외 처리] 숫자가 아닌 경우(ex. 123.45.32, %3421)
    - [예외 처리] 음수인 경우(ex. -1000)
- 딜러와 플레이어들에게 카드 두 장씩을 지급한다.
    - 딜러는 한 장의 카드만 공개한다
    - 플레이어는 모든 카드를 공개한다.
- 각 플레이어는 카드를 더 받을지 말지 결정한다.
- 플러이어들의 모든 결정이 끝나면 딜러의 카드 수령 여부를 체크한다. (16이하이면 카드 수령)
- 최종 결과를 출력한다.
- 최종 수익을 출력한다.
