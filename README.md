# 주어진 기능 요구사항

- 가지고 있는 카드의 합이 21이면 승리한다.
  - 혹은 가지고 있는 카드의 합이 21에 가까운 사람이 승리한다.
- 게임을 시작하기 전에 베팅 금액을 정해야한다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 한다.
  - Ace는 1 또는 11로 계산할 수 있다.
  - K, Q, J는 10으로 계산한다.
- 게임 시작시 2장을 기본적으로 지급받는다.
- 숫자의 합이 21을 넘지 않는다면 카드를 계속 뽑을 수 있다.
- 21을 초과할 경우 베팅 금액을 모두 잃는다.
- 첫 두 장의 카드 합이 21인 블랙잭이 된다면 베팅 금액의 1.5배를 딜러에게 받는다.
  - 딜러와 플레이어가 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
- 딜러는 첫 두장의 합계가 16이하면 반드시 1장의 카드를 추가로 받아야한다.
  - 두장의 합계가 17점 이상이라면 받지 못한다.
  - 딜러의 합계가 21을 초과한다면 무조건 모든 플레이어는 승리한다.



# 예외상황

- 이름을 입력하지 않는 경우
- 배팅금액이 0 이하인 경우
- 한장의 카드를 더 받는 command를 잘못 입력할 경우



# 역할과 책임 (요구사항 분석)

### 역할

- 참여자
  - 딜러
  - 플레이어
- 플레이어 무리(플레이어 일급 컬렉션)
- 카드
- 카드뭉치(카드 일급컬렉션)
- 금액
- 카드 분배기
- 블랙잭 게임
- 게임결과

### 책임

- 참여자(Gamer) (딜러, 플레이어 공통)
  - 아는것
    - 자신의 카드 현황
  - 하는것
    - 카드를 카드 뭉치에 추가하기
- 딜러
  - 하는것
    - 우승자 판별
- 플레이어
  - 아는것
    - 이름
    - 자신의 베팅 금액
- 참여자들
  - 아는것
    - 블랙잭인 참여자가 있는지
  - 하는것
    - 플레이어가 우승한 사람인지 확인하기
- 카드
  - 아는것
    - 카드의 값 (심볼)
    - 카드의 종류 (타입)
- 카드 뭉치
  - 아는것
    - 뭉치에 들어있는 카드
  - 하는것
    - 카드뭉치에 카드 추가하기
    - 뭉치 카드의 합계
    - 21을 초과했는지 여부
- 금액
  - 아는것
    - 베팅금액
  - 하는것
    - 베팅금액 유효성 검사
    - 게임 결과에 따라 다음 금액 값 반환
      - 1.5배 (블랙 잭)
      - 0원 (push)
      - 이득 (+100%)
      - 손해 (-100%)
- 카드 분배기
  - 아는것
    - 모든 카드
  - 하는것
    - 카드 발급
- 블랙잭 게임
  - 아는것
    - 딜러
    - 참가자들
  - 하는것
    - 게임 진행
    - 게임 결과 발표
    - 카드발급기를 이용해 플레이어에게 카드 발급
- 게임결과
  - 이름과 얻은 금액



# 시나리오

1. 플레이어 생성
   - 이름과 베팅 금액 입력 
     - 플레이어 생성
   - 자동으로 딜러생성
2. 첫 두장 카드 분배
   - 블랙잭 존재 확인
3. 각 참여자의 카드 발급 진행
   - 게임 종료 조건
     - 모두 '카드를 받지 않는다.' 를 선택한 경우
     - 딜러의 숫자 합 21초과
     - 플레이어의 숫자 합 21 초과
     - 플레이어의 숫자 합 21 도달
     - 딜러의 숫자 합 21 도달
4. 결과 발표



## Git Commit Convention

[Angular Js Git convention](https://gist.github.com/stephenparish/9941e89d80e2bc58a153) 를 일부 변형하여 사용

------

#### Format

```
<type> : <subject>
<BLANK LINE>
<body>
```



#### Type

- feat (feature)
- fix (bug fix)
- docs (documentation)
- style (formatting, missing semi colons, …)
- refactor
- test (when adding missing tests)



#### body

- includes motivation for the change and contrasts with previous behavior



#### subject

- use imperative, present tense: “change” not “changed” nor “changes”
- don't capitalize first letter
- no dot (.) at the end