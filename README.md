# 프리코스 3주차 블랙잭 
기능 요구사항
------
#### Card
```
- 해당 카드에 대한 점수를 확인 (총 52장)
- ACE 카드의 경우는 유리하게 계산될 수 있게 알려주는 기능
- King, Queen, Jack 카드는 10
- 스페이드, 클로버, 하트, 다이아몬드 각각 2~10 카드
- 초기 카드 생성
- 카드를 뽑아주는 기능
- 중복된 카드가 없도록 컨트롤하는 기능 
```
#### ProgressGame
```
- 게임행의 턴을 진행 
```

#### Player
```
- 플레이어가 소유한 카드들의 점수를 가져오는 기능
- 플레이어가 소유한 카드들을 출력할 수 있게 반환하는 기능
- 플레이어의 판돈을 기억하는 기능 
- 카드를 뽑는 경우에 따라 카드를 뽑지않거나 뽑고 저장하는 기능
- 총 점수가 21을 넘는지 확인하는 기능
- 블랙잭임을 판단하는 기능 
- ACE카드의 경우 유리하게 점수를 적용하는 기능 


상속받은 Dealer
- 딜러임을 표현하는 기능
- 딜러의 촘 점수를 16을 기준으로 나누는 기능
- 처음 2장의 합이 16 이하이면 반드시 뽑는다 
- 처음 2장의 합이 17 이상이면 무조건 스테이 
- 딜러의 카드를 공개 / 비공개 처리하는 기능 
```

#### EntryManager
```
- 플레이어들의 객체를 생성
- 딜러의 객체 생성 
- 플레이어들의 이름을 반환하는 기능 
- 각 플레이어의 카드 정보를 반환하는 기능
- 각 플레이어의 총 점을 반환하는 기능
- 블랙잭, Burst 판단하는 기능
- 승자 / 패자를 판단(공 포함)하고 구분하는 기능 
  + 딜러와 플레이어 둘 다 Burst이면 딜러가 승리
```

#### Calculator
```
- 승자 / 패자에 따른 판돈 정산 및 반환 
- 블랙잭인 플레이어는 판돈의 1.5배를 얻고 반환 
- 딜러와 플레이어가 동시에 블랙잭이면 블랙잭인 플레이어 제외 모두 판돈을 잃는다
  + 동시에 블랙잭인 플레이어는 판돈만 받는다
- 플레이어가 Burst인 경우 판돈을 잃고 반환 (-)
```

#### UI
```
입력
- 참가자를 입력받기 
- 여라 참가자를 쉼표로 구분하여 전달(유효하지 않으면 반복)
  + 블랙잭 참여자는 보통 2~8명임을 고려할 것
- 각 참가자별 판돈 입력받기(유효하지 않으면 반복)
- 카드를 더 받을 것인지 여부 입력받기(유효하지 않으면 반복)


출력 
- 딜러, 참여자목록, 최초 나눠준 카드 수 출력 
- 딜러의 카드 출력 
- 각 플레이어의 카드 출력 
- 카드를 더 받을지 여부 출력 
- 딜러의 행동 출력 
- 딜러와 각 플레이어의 최종 카드, 점수 결과 출력 
- 딜러와 각 플레이어의 최종 수익 출력 
```
프로그래밍 요구사항
------
#### A. 기본 요구사항 
```
- 주어진 객체를 활용 
- 기본 객체에 대 예외처리 (선택)
- Player와 Dealer 중복 코드를 상속으로 처리 (선택)

- 자바 코드컨벤션을 지킨다
- 3항 연산자를 쓰지 않는다
- else 예약어를 쓰지 않는다
- 매소드의 길이는 10라인 이내로 구현하고 인자는 3개 이내로 구현한다
```
#### B. 피드백을 참고한 요구사항 
```
- 값을 하드코딩하지 않는다
- 축약보단 의도를 확실히 드러낸다
- API, collection 을 적극적으로 활용한다
- 객체에 메세지를 보내라
- 비즈니스 로직 / UI 로직의 분리
```