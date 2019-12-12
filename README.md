# 블랙잭 - 김경준

## 구현할 기능 목록

- [x] 게임에 참여할 플레이어를 입력 받는다, 쉼표 기준으로 분리 해야한다.
- [x] 입력 받은 플레이어의 순서에 맞게 각각의 배팅 금액을 입력 받는다.
- [x] 입력 받은 이름과 금액에 맞는 Player를 생성한다.
- [x] 게임에 사용될 카드뭉치 (deck) 을 생성한다.
- [x] 배팅 금액을 입력 받은후 딜러에게 1장, 플레이어에게 2장의 카드를 나눠준다.
- [x] 카드를 받은 사람의 카드 상황을 보여준다.
- [x] 입력 받은 플레이어의 순서에 맞게 카드를 더 받을 것인지의 여부를 입력 받는다.
- [ ] 모든 플레이어가 카드를 받지 않을 경우 딜러가 카드를 받기 시작한다.
- [ ] 딜러는 16점 이하일 경우 카드를 받는다 17점 이상이면 받을 수 없다.
- [ ] 플레이어는 처음 2장이 21점일 경우 베팅 금액의 1.5배를 딜러에게 받는다. 동시에 블랙잭인 경우 베팅 금액을 그대로 받는다.
- [ ] 딜러가 21일 초과하면 플레이어는 가지고 있는 패에 상관 없이 베팅 금액을 받는다.

## 예외 처리 목록

- [ ] 게임에 참여할 플레이어를 입력 받을때, 적절하지 않은 문자열을 입력할 경우
  - [ ] "," 사이에 문자가 존재하지 않을 때
- [ ] 배팅 금액을 입력 받을 때, 적절하지 않은 숫자를 입력할 경우
  - [ ] 음수일 경우
- [ ] 카드를 더 받을지 여부를 물을때 y, n이 아닌 다른 문자를 입력할 경우