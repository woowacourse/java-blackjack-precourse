# 블랙잭
## 프로그램 소개
- 블랙잭 게임을 진행하는 프로그램. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 플레이어는 게임을 시작할 때 배팅금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅금액을 모두 잃게 된다.
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅금액의 1.5배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅 한 금액을 돌려받는다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아있던 플레이어들은 가지고 있는 패에 상관없이 승리해 베팅금액을 받는다.

## 구현할 기능 목록
1. 각 플레이어의 정보를 입력받는다.
    * 플레이어는 쉼표 기준으로 분리한다.
    * [예외처리] 빈 이름이 있을 경우 (ex. a,,b,c)
    * [예외처리] 입력이 올바르게 끝나지 않았을 경우 (ex. a,b,c,)
    * 각 플레이어의 배팅 금액을 입력한다.
    * [예외처리] 숫자가 아닌 수를 입력한 경우
    * [예외처리] 음수를 입력한 경우

1. 카드를 초기화한다.
    * 카드는 Ace,2~10,K,Q,J의 심볼로 이루어져있다.
    * 카드는 스페이드, 클로버, 다이아몬드, 하트의 타입을 갖는다.

1. 딜러와 플레이어에게 카드를 배분한다.
    * 딜러와 플레이어에게 2장씩 분배한다.
    * 딜러는 한 장의 카드만 공개한다.
    * 플레이어는 두 장 모두 공개한다.
    
1. 플레이어들의 추가 카드를 지급한다.
    * 한 장씩 카드를 더 받는다.
    * 각 플레이어가 더 받지 않을 때까지 질문한다.
    * 모든 플레이어의 추가 카드 지급을 마치면 마친다.
    
1. 딜러의 카드 상황을 확인한다.
    * 딜러의 점수 합이 16이하면 추가로 카드를 지급한다.
    * [유의사항] 추가로 받았는데도 16이하라면 16이상이 될 때까지 한 장씩 계속 지급받는다.
    
1. 딜러와 플레이어들의 카드, 점수를 출력한다.
    * 점수는 카드의 숫자로 계산한다. 
    * King, Queen, Jack은 10으로 계산한다.
    * Ace는 1 또는 11 중 유리한 숫자로 계산한다. 유리한 숫자는 21을 넘지 않고 21에 가까운 수이다.
    
1. 배팅 금액을 계산하여 최종 수익을 출력한다.
    * 21에 가장 가까운 사람이 우승이다.
    * [유의사항] 우승자가 한 명 이상일 수 있다.
    * 우승하지 못할 경우 배팅 금액을 모두 딜러에게 지불한다.
    * 21을 초과할 경우 배팅 금액을 모두 딜러에게 지불한다.
    * 처음 두 장의 카드 합이 21일 경우 베팅 금액의 1.5배를 딜러에게 받는다.
