# 기능요구사항

1. 블랙잭 게임을 진행하는 프로그램을 구현한다. 
   블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

2. 플레이어는 게임을 시작할 때 배팅금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 
   예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.

3. 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 
   두장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
   21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 
   단,카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.

4. 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5배를 딜러에게 받는다. 
   딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.

5. 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 
   딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

# 기능구현과정

1. 카드 인덱스 리스트를 만들어 랜덤으로 뽑은 카드 내용을 저장하는 방식으로 진행
> 다만 과정 중간에 사용자의 정보가 필요하다고 판단하여 잠시 중단하고 사용자 구현을 진행함

2. 사용자 입력부분을 구현함 
> 이때 ,, 혹은 aa,,bb 같이 공백이 있는 입력에 대해서는 예외처리를 진행하여 다시 사용자 입력부분을 실행하도록 구현

3. 배팅금액 입력부분을 구현함
> 이때 숫자가 아닌 문자 입력에 대한 예외처리를 진행함

4. player 리스트를 생성하고 각각에게 초기 2장의 카드를 제공하는 부분을 구현함
