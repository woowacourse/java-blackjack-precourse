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

<<<<<<< HEAD
# 기능구현과정

1. 카드 인덱스 리스트를 만들어 랜덤으로 뽑은 카드 내용을 저장하는 방식으로 진행
> 다만 과정 중간에 사용자의 정보가 필요하다고 판단하여 잠시 중단하고 사용자 구현을 진행함

2. 사용자 입력부분을 구현함 
> 이때 ,, 혹은 aa,,bb 같이 공백이 있는 입력에 대해서는 예외처리를 진행하여 다시 사용자 입력부분을 실행하도록 구현

3. 배팅금액 입력부분을 구현함
> 이때 숫자가 아닌 문자 입력에 대한 예외처리를 진행함

4. player 리스트를 생성하고 각각에게 초기 2장의 카드를 제공하는 부분을 구현함
<<<<<<< HEAD
=======
# 기능구현순서

>>>>>>> eff8e0347dadf1608087ee5f076b723bff1c248d
=======

5. 카드를 추가하는 부분을 완성하고 카드 추가시에 21이 넘을 경우 뽑지 못하는 조건을 추가함

6. ACE가 존재할 때와 존재하지 않을 때의 점수를 계산하는 부분을 추가함

7. 딜러 부분을 구현
> 상속의 개념을 정확히 활용하지 못하여 같은 내용을 반복함
> 추가적으로 player와 다른 뽑기 방식의 조건부분을 수정해줌

8. 배당을 곱해주는 부분을 구현
> 비교하는 부분의 우선순위를 명확히 구분하지 못하여 자신의 대해서만 결과를 출력함. 딜러의 경우는 배팅금액이 0

# 느낀 점
지난 2주간의 과제와 다르게 많은 제약조건을 지켜며 문제를 해결하기위해 비효율적인 방식의 코드를 작성했다고 생각합니다.
또한 중간중간 내용을 정리하면서 진행하였지만, 구현해둔 메소드가 늘어나면서 어려움을 많이 느꼈습니다. 또한, 이번 과제에 선택사항인
상속에 대한 정확한 이해가 되지 않아 dealer 구현에 비효율적으로 같은 코드들이 반복되었습니다.
앞선 두개의 과제를 바탕으로 할 수 있다는 생각을 가졌지만, 이번 과제를 통해 아직 갈 길이 많이 멀다는 것을 느낄 수 있었습니다.
지금까지 3주동안 진행된 프리코스 과정을 바탕으로 더 좋은 코드를 작성할 수 있도록 노력해보려고 합니다.
이번 과제는 완성된 코드를 제출하지 못했습니다. 하지만, 더이상 이 코드로 진행하는 것이 무의미하다고 느낄정도로 정돈이 안되어있다는 생각을 하여 미리 제출 후 새로 진행하려고 합니다. 
>>>>>>> e78f398fcd2e837f0e4aa2ac9b74d13867ea1f69
