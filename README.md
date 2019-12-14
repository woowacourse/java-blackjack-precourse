# 우아한 테크코스 프리코스 3주차 블랙잭 게임

## 준비

- JDK(>=8)
- gradle or IntelliJ
- AssertJ 3.14.0
- JUnit 5

## TODO

### issue

- 블랙잭은 트럼프덱 몇개를 사용하는가?
- 이 미션에선 몇개의 덱으로 미션을 진행해야하는가?

### 프로그래밍시 지켜야할 것

- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
- indent depth (< 2)
- 3항연산자 금지
- else 예악어 금지
- 하나의 메소드는 하나의 일만하도록
- 하나의 메소드의 라인수 (< 10)
- 객체에 메세지를 보내라

### Class , methods

#### domain

##### Card

- [x] 이 카드의 점수를 가져오는 기능
- [x] ACE 카드인지 여부 확인하는 기능

##### Player

- [x] Dealer 와 상속관계.
- [x] Dealer 객체의 필드(이름)은 "딜러"
- [x] 자신이 가진 카드들 String 으로 이쁘게 반환해주는 기능
- [x] 자신이 가진 카드들 점수 가져오는 기능
- [x] 자신이 Burst(점수 > 21) 인지 확인하는 기능
- [x] 자신이 블랙잭인지 확인하는 기능
- [x] 카드를 분배받을시 카드를 잘 받아 저장하는 기능
- [x] 자기가 딜러인지여부 확인해주는 기능
- [x] 딜러의 (점수 <= 16) 인지 확인하는 기능
- [x] ACE 카드 존재 정보 가져오는 기능
- [x] ACE 카드는 1 또는 11로 점수가져오도록
- [x] Player(Dealer) 끼리 점수 비교 기능
- [x] Player(Dealer) 초기 카드 받는 기능
- [x] 카드더받기 여부 확인받아 카드 더받음
- [x] Burst 이면 카드 더 받을수 없음
- [x] 딜러는 카드 더받을때 안받음 (나중에받음)
- [x] 딜러의 카드 더받는 기능
- [x] 플레이어들 카드 다받을시 딜러 히든카드 보이는(받는) 기능
- [x] 자신의 점수 가져오는 기능
- [ ] 스코어를 Burst 나 BlackJack 으로 반환하는 기능
- [x] 딜러의 BlackJack 체크 기능
- [x] Black jack 이면 추가카드 못받도록 하기

##### Deck (게임내 소유되지않은 카드들 관리)

- [x] 카드한장 뽑아주는 기능

##### Gamers (게임하는 사람들 관리 객체)

- [x] Player(Dealer) 초기 카드 받는 기능
- [x] 플레이어 전체 이름 반환하는 기능
- [x] 플레이어 전체 이름, 카드 toString 기능
- [x] 딜러 호출하는 기능
- [x] 플레이어들 Burst, Blackjack 인 플레이어 가져오는 기능
- [x] 플레이어 죽이는(die) 기능 추가.
- [x] 플레이어들과 플레이어 점수 비교하는 기능 추가

##### Game

- [x] 게임을 돌리는 기능
- [x] 각 플레이어별 카드 더 받도록 도와주는 기능

##### StakeManager 판돈정산 기능

- [x] 플레이어 burst 는 -판돈 반환
- [x] 플레이어 블랙잭은 판돈의 1.5배 추가 반환
- [x] 플레이어 && 딜러 블랙잭은 판돈돌려받기 
- [ ] 플레이어 딜러 비교 하여 +판돈 또는 -판돈 반환

#### view

##### input

- [x] 참가할 사람 입력받기.
- [x] 불필요 입력 예외처리
- [x] 사람입력 "," 로 구분하여 보내기.
- [x] 배팅금액 입력 받기
- [x] 배팅금액 예외처리 해서 보내기.
- [x] 카드 더받을지 여부 입력받기
- [x] 카드 더받을지 여부 예외처리하기

##### output

- [x] 게임참여출력
- [x] 배팅금액 물어보기 출력
- [x] 초반 카드 나누기 출력
- [x] 각 플레이어(+딜러)의 소유한 카드 출력
- [x] 각 플레이어에게 카드 더받을지여부 출력
- [x] 딜러 점수가 16 이하여서 한장 더받음 출력
- [x] 각플레이어 소유한 카드와 결과 같이 출력
- [x] 플레이어 lose 는 수익 -1배 출력
- [x] 플레이어 블랙잭은 수익 1.5배 출력
- [x] 플레이어 win 은 수익 1배 출력
- [x] 플레이어 draw 는 수익 0배 출력

### Test

#### domain

##### Card

- [x] POJO test

##### Player

- [x] POJO test

#### view