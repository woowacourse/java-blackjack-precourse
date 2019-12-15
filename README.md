# [블랙잭] 프로젝트

#### 플레이어가 지급받은 2장의 카드와 카드 추가 법칙을 이용해 카드의 합을 21로 만들거나, 딜러의 카드 합보다 21에 가까운 21 이하의 숫자를 만들어야 베팅 금액을 획득할 수 있는 게임이다.



# [블랙잭]의 기능 목록

#### ▦ 사용자에게 게임에 필요한 문자열을 입력 받아 적합한 데이터인지 검사한다.

- **_플레이어 이름_**
  - 문자열을 쉼표(,) 기준으로 분리했을 때 각각의 문자열이 플레이어의 이름이다.
  - 모두 공백으로 이루어진 이름이 있어서는 안된다.
  - 중복되는 이름이 있어서는 안된다.

- **_베팅 금액_**
  - 모든 플레이어는 베팅 금액이 있어야 한다.

  - 베팅 금액인 문자열은 숫자여야 한다.
  - 숫자인 경우 0을 초과하는 숫자여야 한다.

- 입력한 문자열이 모두 적합한 데이터로 판명이 나면 플레이어를 생성한다.

#### ▦ 딜러와 플레이어에게 게임 카드를 2장씩 준다.

- 카드 내용이 똑같은 게임 카드는 없다.
- 게임 카드는 무작위로 섞은 뒤에 줄 수 있다.

#### ▦ 딜러와 플레이어가 받은 카드의 내용을 출력한다.

- 카드의 내용은 심볼+타입의 형태로 출력된다.
- 딜러의 카드는 1장의 내용만 출력된다.
- 플레이어의 카드는 2장의 내용이 모두 출력된다.

#### ▦ 플레이어가 입력한 대답 문자열에 따라 카드를 추가하거나 추가하지 않는다.

- 플레이어가 카드 추가 여부 메세지에 y와 n 이외의 문자열을 입력하면 대답 문자열 재입력을 요청한다.
- y를 입력하면 무작위로 카드 1장이 플레이어의 카드에 추가된다. y 입력 회수에는 제한이 없다.
- 카드가 추가될 때마다 추가된 카드를 포함하여 플레이어가 갖고 있는 카드 내용이 모두 출력된다.
- n을 입력하면 카드는 추가되지 않고 플레이어가 갖고 있는 카드 내용이 모두 출력된 후 다음 플레이어에게 카드 추가 여부 대답 문자열을 입력 받는다. 다음 플레이어가 없으면 딜러의 카드 추가 여부가 출력된다.
- 카드 숫자의 합이 21이면 최대 합임을 출력하고 다음 플레이어에게 카드 추가 여부 대답 문자열을 입력 받는다. 다음 플레이어가 없으면 딜러의 카드 추가 여부가 출력된다.
- 카드 숫자의 합이 21을 넘으면 플레이어의 패배 알림을 출력하고 다음 플레이어에게 카드 추가 여부 대답 문자열을 입력 받는다. 다음 플레이어가 없으면 딜러의 카드 추가 여부가 출력된다.

#### ▦ 딜러의 카드 숫자의 합에 따라 카드를 추가하거나 추가하지 않는다.

- 딜러가 가진 카드 숫자의 합이 16 이하면 무작위로 카드 1장이 딜러의 카드에 추가된다.
- 딜러가 가진 카드 숫자의 합이 17 이상이면 카드는 추가되지 않는다.
- 딜러의 카드 추가 여부 사항을 출력한다.

#### ▦ 딜러와 플레이어가 가진 카드 숫자의 합을 각각 계산하여 출력한다.

- 딜러와 플레이어가 갖고 있는 모든 카드 내용이 출력되고 그 다음 계산된 카드 숫자의 합이 출력된다.
- **_카드 숫자 기준_**
  - 갖고 있는 카드가 지닌 숫자가 기본이 된다.
  - King, Queen, Jack은 10이다.
  - Ace는 Ace를 제외한 카드 숫자의 합이 11 이상이면 1이 되고, 11 미만이면 11이 된다.

#### ▦ 게임 규칙에 따른 딜러와 플레이어의 최종 수익을 출력한다.

- **_블랙잭이 있는 경우_**
  - 블랙잭이란 갖고 있는 카드가 2장이면서 카드 숫자의 합이 21인 경우를 가르킨다.
  - 딜러는 블랙잭이고 플레이어도 블랙잭이면 플레이어는 수익도 손해도 없다.
  - 딜러는 블랙잭이 아니고 플레이어는 블랙잭이면 플레이어는 베팅 금액의 1.5배를 수익으로 얻는다. 
  - 딜러는 블랙잭이고 플레이어는 블랙잭이 아니면 플레이어는 베팅 금액을 잃는다.

- **_버스트가 있는 경우_**
  - 버스트란 갖고 있는 카드 숫자의 합이 21을 넘는 경우를 가르킨다.
  - 플레이어가 버스트이면 플레이어는 베팅 금액을 잃는다.
  - 딜러가 버스트이고 플레이어는 버스트가 아니면, 플레이어는 베팅 금액을 수익으로 얻는다.


- **_딜러의 카드 숫자의 합과 플레이어의 카드 숫자의 합을 비교하는 경우_**
  - 딜러의 카드 숫자의 합보다 플레이어의 카드 숫자의 합이 크면 플레이어는 베팅 금액을 수익으로 얻는다.
  - 딜러의 카드 숫자의 합과 플레이어의 카드 숫자의 합이 같으면 플레이어는 수익도 손해도 없다.
  - 딜러의 카드 숫자의 합보다 플레이어의 카드 숫자의 합이 작으면 플레이어는 베팅 금액을 잃는다.

- 플레이어의 수익은 딜러의 손해이고, 플레이어의 손해는 딜러의 수익이 된다.

