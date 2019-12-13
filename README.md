# 💵 블랙잭 게임 ♠ 

- 블랙잭 게임 미션을 진행하는 저장소

## 준비물 ♦

- JDK(>=8)
- gradle or IntelliJ
- AssertJ 3.14.0

## 주의사항 ♥
- 3항 연산자 사용하지 않기
- indent depth 최대 1까지만 허용
- 자바 코드 컨벤션을 지키면서 프로그래밍 하기
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현하기
- else 예약어를 사용하지 않기
- 함수(또는 메서드)의 인자 수를 3개까지만 허용

## 기능 구현 목록 ♣

1. Player Class

    플레이어의 정보를 관리하는 클래스

    - Dealer 클래스를 Player 클래스에 상속되도록 한다.
    - 플레이어 이름, 배팅금액을 인자로 받는 생성자
    - 플레이어에게 부여된 카드를 관리하는 메서드
    - 랜덤으로 카드를 섞고 뽑는 메서드
    - 가지고 있는 카드의 수를 모두 합하는 메서드
        - ACE 를 가진 경우 도합 22가 넘어가면 1로 계산
        - ACE 를 가진 경우 도합 21 이하면 11로 계산

2. Card Class

    카드를 관리하는 클래스

    - Symbol 과 Type 을 조합하여 카드 객체를 생성
    - 카드가 일치하는지를 확인하는 메서드
    - 카드 정보를 확인하는 메서드
    - 카드정보를 String 으로 반환해주는 메서드

3. CardFactory Class

    카드를 생성하는 클래스
    
    - 카드의 문향을 생성하는 메서드
    - 카드의 타입을 생성하는 메서드

4. Symbol Enum Class

    카드 문향을 담고있는 enum Class
    
    - 점수를 가져오는 메서드

5. Type Enum Class

    카드 수를 담고있는 enum Class
    
6. Manager

    게임을 관리하는 클래스
    
7. Validator

    입력된 값의 유효성을 확인하는 클래스
    
    - 공백('space')이 포함되어있는지를 확인하는 메서드
    - 0이하의 수치가 포함되어있는지를 확인하는 메서드
    
8. Deck

    게임 시작 후 카드 상태를 관리하는 클래스
    
    [참고 사항] 본 게임은 덱 한벌로 진행하는 블랙잭을 구현함
    
    - 카드를 한 장 제거하는 메서드
        - 덱을 여러벌 사용할 경우 remove 메서드를 사용하지 않으면 됨
        - 사용하는 덱의 수만큼 덱을 생성하면 카운팅 방지를 위한 유연한 게임진행 가능
    
9. Table

    게임에 참여한 딜러, 플레이어를 관리하는 클래스
    - 객체 생성 시 자동으로 딜러를 추가
    - 플레이어 추가 메서드
    - 게임 결과 판돈을 계산하는 메서드
        - 플레이어가 벌어들인 수익을 계산하는 메서드
        - 딜러가 벌어들인 수익을 계산하는 메서드
    
10. Output

    게임에 출력을 담당하는 클래스