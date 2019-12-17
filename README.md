## 기능 목록

### 공통 확인 부분
1. 자바 코드 컨벤션 확인
    - Space(공백) Convention 확인
        - 키워드 사이에 Space
    - 구현 순서 Convention
        ````
        Class A {
            상수 및 변수
            인스턴스 변수
            생성자
            메소드
       }
        ````
    - code format(Ctrl + Alt + L)
2. indent(인덴트, 들여쓰기) depth 2가 넘지 않도록 확인
3. 3항 연산자 사용불가
4. 식별자를 통해 의도를 드러내기(축약X)
    - 의미없는 주석 해결 방안
5. 불필요한 공백 라인 금지
6. space, tab 둘중에 하나만 쓰기
7. 값 하드코딩 금지
    - 상수를 만들고 이름을 부여
8. git commit 메시지를 의미있게 작성
9. 기능 목록 추가 업데이트
    - 예외적인 상황도 기능 목록에 정리
10. 함수(메소드) 길이가 10라인을 넘어가지 않도록 구현
    - 한가지 일만 하도록 최대한 작게 만들기
    - 공백도 라인에 포함
    - 주석을 가능하면 함수 밖 또는 코드 우측에 추가하는 방식으로 표현
11. else 예약어 금지
    - if에서 값을 return하는 방식으로 구현
    - switch/case 금지
12. 중복코드 제거
    - 상속을 활용
    - 필드 중복 제거(최소한만 사용)
13. 인자 수를 3개까지만 허용
14. 자바 API 적극 활용
    - Java Collection
15. 객체에 메시지 보내라(꺼내려고 하지 말고 객체에 메시지(인자) 보내기)
16. 비즈니스 로직과 UI 로직을 분리
17. git을 통해 관리할 자원에 대해서 고려
    - .gitignore에 추가할것들을 고려하여 추가
18. 미리 구현 해 놓은 Class들은 수정 불가, 추가만 가능
    - 기본생성자, 필드 접근제어자
    - 필드는 추가도 불가

### 기능 구현 순서

1. Input Class
    - Player Name 입력받는 메소드
        - 쉼표 기준으로 받기
        - 영대소문자만 입력 가능
        - 비어있는 값 불가
        - 공백 사용 불가
    - 배팅 금액 받기 메소드
        - 숫자 외 다른 값이 들어오면 안됨
    - 카드 추가 여부 메소드
    - Ace카드 1 or 11 여부 메소드

2. Output Class
    - 시작상황 출력 메소드
        - 딜러 카드는 한개 공개
        - 플레이어 카드는 모두 공개
    - 딜러 추가 카드 출력 메소드
    - 최종 카드 결과 출력 메소드
        - 딜러와 플레이어가 가지고 있는 카드 출력
        - 카드 숫자 출력
    - 최종 수익 결과 출력 메소드
    
3. Game Class
    - 실질적으로 전체 게임이 실행되는 메소드
    - 각 Player 객체 생성 메소드
    - 시작 결과 계산 메소드
        - 처음 두장의 합이 21일 경우 딜러에게 1.5배 받기
    - 결과 계산 메소드
        
4. Player Class
    - Helper Class 상속
    - 추가카드 지급 메소드
    
5. Dealer Class
    - Helper Class 상속
    - 추가 카드 메소드
        - 16이하이면, 반드시 1장 추가로 받고 17 이상이면 추가로 받아야 함
    
6. Helper Class
    - 숫자 계산
    - 카드 받기
    - 가지고 있는 카드 출력
      
7. MainGame Class
    - 게임실행

8. 공통 확인 부분 다시 한번 확인!