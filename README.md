## 기능
- 게임 시작시, 52장의 카드 더미가 생성된다. [O]
- 각 카드는 symbol로 스페이드, 다이아몬드, 하트, 클로버 중 하나를 갖는다. [O]
- 각 카드는 type으로 1~10, J, Q, K, A 중 하나를 갖는다. [O]
- 딜러는 쉼표로 구분된 이름을 입력받아야한다. [O]
    - 이름이 10자를 넘기지 않는지, 영어 알파벳 이외의 문자가 쓰이지 않았는지 검증할 수 있어야한다. [O]
- 딜러는 각 플레이어들에게 배팅금액을 물어봐야한다. [O]
    - 숫자가 자연수인지 확인할 수 있어야한다. [O]
- 입력받은 이름과 배팅 금액으로 Player 객체를 생성할 수 있어야한다. [O]
- 딜러는 플레이어 또는 딜러에게 카드를 나눠줄 수 있어야한다. [O]
- 플레이어와 딜러는 자기의 카드 합을 계산할 수 있어야한다. [O]
    - J, Q, K는 10으로 계산한다. [O]
    - A는 1 또는 11 중 유리한 쪽으로 계산한다. [O]
- 처음 나눠준 2장의 카드에 블랙잭이 있다면 다음 중 하나를 처리하고 게임을 종료한다. [O]
    - 플레이어(들)만 블랙잭일 경우, 블랙잭인 플레이어(들)에게 배팅한 금액의 1.5배를 돌려준다. [X]
    - 딜러만 블랙잭일 경우, 게임을 종료한다. [X]
    - 플레이어(들)과 딜러 모두 블랙잭일 경우, 블랙잭인 플레이어의 돈은 돌려주고 패배한 플레이어의 돈은 딜러가 갖는다. [X] 
- 플레이어는 자기의 카드 합이 21 이하이면 카드를 더 받을 수 있어야한다. [X]
- 플레이어의 카드 합이 21을 초과하면 해당 플레이어는 패배하고 게임에서 제외된다. [X]
- 딜러는 플레이어가 카드를 더 받길 원하는지 묻고, 원한다면 카드를 줘야한다. [X]
    - 입력은 y 또는 n만 허용하며 올바른 입력이 들어왔는지 확인할 수 있어야한다. [X]
- 딜러는 자기의 카드 합이 16이하면 카드를 더 받아야하고, 17이상이면 카드를 더 받을 수 없다. [X]
- 딜러의 카드 합이 21을 초과하면 게임을 진행하고 있는(제외되지 않은) 플레이어들에게 배팅금액을 돌려준다. [X]
 (게임이 종료된 플레이어의 돈은 딜러가 갖는다)
- 딜러는 어떤 플레이어(들) 또는 딜러가 승리했는지 확인할 수 있어야한다. [X]
- 딜러는 승리한 플레이어(들)에게 (배팅한 금액 * 2) 만큼의 배팅금액을 나눠준다. [X]
- 화면에 최종수익이 출력된다. [X]
