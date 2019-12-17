# 우아한 테크코스 프리코스 3주차 미션 - 블랙잭

##### version 0.9  

##### 과정을 정리해둔 노션 링크 : https://www.notion.so/toneyparky/WOO-WA-Tech-Course-fb6c6d1c14354d38bafe252e8f42b7f5

#### 프로그램 개요 :  
이 프로그램은 우아한 테크코스 프리코스 3주차 미션인 블랙잭게임을 기능/프로그래밍 요구사항에 맞춰 구현했다.


#### 기능 요구사항 :
- 블랙잭 게임을 진행하는 프로그램을 구현한다.    
블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가 까운 숫자를 가지는 쪽이 이기는 게임이다.
- 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.  
 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가 깝게 만들면 이긴다.  
 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.  
  단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.  
 딜러와 플레이어가 모두 동시 에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.  
 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받 는다.

#### 깃 커밋 키워드 :  
- Complete : 기능구현 완료시 (헤드)
- Add: 클래스나 파일이 추가될 경우 (바디)  
- Fix: 기능 버그의 수정 
- Modify: 가독성과 로직을 위한 코드 수정
- Update : 문서 업데이트 (README.md, javadoc, ETC)  
- Refactor : 리팩토링

#### 기능 목록 :
- 메인 실행 기능  
- [x] 프로그램을 실행한다.

- 블랙잭 게임 컨트롤 로직 기능 (이 기능을 구현하는 것이 메인이다)
- [x] 사용자로부터 받은 입력으로 사용자 인스턴스를 만든다.
- [x] 카드를 두장씩 뽑는다. (중복처리)
- [x] 결과를 확인하고 종료되지 않으면 참여자들과 딜러가 카드를 더 받게 한다. 
- [x] 블랙잭 발생 여부를 컨트롤한다. 
- [x] 버스트 발생여부를 컨트롤한다.   
- [x] 최종 결과를 출력한다.
- [x] 최종 수익을 출력한다.
   
- 블랙잭 모델 기능  
- [x] Ace카드를 11로 볼지 1로 볼지 결정한다. (기본적으로 11로 놓고 버스트 발생시 1로 바꾸기)  
- [x] Dealer와 Player 클래스 상위에 HumanInCasino 클래스를 만든다.

- 출력문 기능
- [x] 사용자에게 참여자 이름을 물어봐야한다.
- [x] 사용자에게 각 참여자의 배팅금액을 물어봐야한다.
- [x] 사용자가 가진 전체 카드 목록을 출력한다.
- [x] 딜러가 가진 카드 목록 중 맨 앞 한장을 출력한다. (처음 오픈에 사용)
- [x] 딜러가 가진 전체 카드 목록을 출력한다.
- [x] 참여자에게 카드를 더 받을지 여부를 물어봐야한다.
- [x] 딜러가 카드를 더 받는지 여부를 출력한다.
- [x] 딜러가 더 받은 카드를 출력한다.
- [x] 딜러가 카드를 더 받지 않는다고 출력한다.
- [x] 각각의 결과를 출력한다.  
- [x] 최종 수익을 출력한다.

- 사용자 입력 기능  
- [x] 사용자에게 이름을 입력받아야한다.
- [x] 사용자가 입력한 참여자 이름을 쉼표 기준으로 분리해야 한다.
- [x] 사용자에게 배팅금액을 입력받아야한다.
- [x] 사용자가 각 참여자의 카드 추가 오픈 여부를 입력해야 한다. (y, n)

- 유효성 검사 기능
- [x] 사용자가 입력한 참여자 이름의 유효성 검사를 해야한다. (예외처리)
- [x] 사용자가 입력한 배팅금액의 유효성 검사를 해야한다. (예외처리)
- [x] 사용자가 입력한 참여자의 카드 추가 오픈 여부에 대하여 유효성 검사를 해야한다. (예외처리)

- 결과 처리 기능
- [x] 블랙잭 여부를 판단한다.
- [x] 버스트 여부를 판단한다.   
- [x] 버스트시 Ace의 값을 조정한다. (예외처리)
- [x] 최종 수익을 계산한다.

- 리팩토링  
- [ ] 프로그램의 흐름을 깔끔하게 바꾼다.  
- [x] 프로그램이 컨벤션에 맞는지 확인하고 리팩토링한다.  