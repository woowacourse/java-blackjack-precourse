# 우아한 테크코스 프리코스 3주차 블랙잭 게임

## 준비

- JDK(>=8)
- gradle or IntelliJ
- AssertJ 3.14.0
- JUnit 5

## TODO

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

- [ ] 예외처리
- [ ] 이 카드의 점수를 가져오는 기능

##### Player

- [ ] Dealer 와 상속관계.
- [ ] Dealer 객체의 필드(이름)은 "딜러"
- [ ] 자신이 가진 카드들 String 으로 이쁘게 반환해주는 기능
- [ ] 자신이 가진 카드들 점수 가져오는 기능
- [ ] 자신이 Burst(점수 > 21) 인지 확인하는 기능
- [ ] 자신이 블랙잭인지 확인하는 기능
- [ ] 카드를 분배받을시 카드를 잘 받아 저장하는 기능
- [ ] 게임결과를 받아와 최종수익을 반환해주는 기능
- [ ] 자기가 딜러인지여부 확인해주는 기능

##### Game

- [ ] 

#### view

##### input

- [ ] 참가할 사람 입력받기.
- [ ] 사람입력 "," 로 구분하여 보내기.
- [ ] 배팅금액 입력 받기
- [ ] 배팅금액 예외처리 해서 보내기.
- [ ] 카드 더받을지 여부 입력받기
- [ ] 카드 더받을지 여부 예외처리하기

##### output

- [ ] 게임참여출력
- [ ] 배팅금액 물어보기 출력
- [ ] 초반 카드 나누기 출력
- [ ] 각 플레이어(+딜러)의 소유한 카드 출력
- [ ] 각 플레이어에게 카드 더받을지여부 출력
- [ ] 딜러 점수가 16 이하여서 한장 더받음 출력
- [ ] 각플레이어 소유한 카드와 결과 같이 출력
- [ ] 최종 수익 출력