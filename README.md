## 블랙잭
### 기능구현목록
#### 입력
1. 게임에 참여할 사람을 입력받는 기능
    1. 입력받은 문자열을 쉼표 기준으로 분리하는 기능
    1. [예외] 공백을 입력하는 경우
    1. [예외] 플레이어 이름이 공백으로 시작하는 경우
    1. [예외] 플레이어 이름이 공백으로 끝나는 경우
1. 참여자의 배팅금액을 입력받는 기능
    1. [예외] 숫자가 아닌 경우
    1. [예외] 0이하를 입력하는 경우
1. 한장의 카드를 받을지 선택을 입력받는 기능
    1. [예외] 문자가 아닌 경우
    1. [예외] 'y'나 'n'이 아닌 경우
    1. [예외] 두글자 이상인 경우


#### 중간과정
1. 플레이어를 추가하는 기능
1. 랜덤으로 숫자를 생성하는 기능
1. 딜러에게 두 장의 카드를 지급하는 기능
1. 플레이어에게 두 장의 카드를 지급하는 기능
1. 카드의 숫자가 21(블랙잭)인지 확인하는 기능
1. 딜러의 카드 숫자가 16이하인지 확인하는 기능
1. 딜러의 카드 숫자가 16이하이면 카드를 받는 기능
1. 카드를 추가로 뽑아도 되는지 확인하는 기능
1. 카드를 추가로 뽑는 기능
1. 최종 수익을 계산하는 기능
    1. 딜러의 카드 숫자가 21초과인지 확인하는 기능
        1. 모든 플레이어에게 배팅금액만큼 수익을 주는 기능
    1. 딜러가 이겼는지 확인하는 기능
    1. 플레이어가 이겼는지 확인하는 기능
    1. 딜러와 플레이어가 비겼는지 확인하는 기능 

#### 출력
1. 최초로 카드를 나누었다는 메시지를 출력하는 기능
1. 참여자들의 카드 보유현황을 출력하는 기능
1. 딜러의 카드가 16이하라 카드를 받았다는 메시지를 출력하는 기능
1. 최종 결과와 카드 현황을 출력하는 기능
1. 최종 수익을 출력하는 기능