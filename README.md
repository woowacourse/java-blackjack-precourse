# 기능구현목록 #

### 사용자 및 배팅금액 입력 ###
* askUserName : 유저 이름을 묻는다
* isValidNames : 쉼표로 구분되어 공백아닌 문자가 되는지 확인 
* saveUserNames : 이름을 리스트로 임시 저장
* askBetMoney : 이름별 배팅 금액 묻기
* isValidMoney : 숫자만 입력했는지 확인
* registerUser : 이름과 배팅금액으로 각각 Player 객체 생성

### 카드 배분 ###
* distibuteInitialCards : 처음에 모든 유저와 딜러에게 두장씩 나눠줌
* getCard(int qty, User user) : 특정 유저에게 qty만큼의 카드 배분
* isAlreadyUsed : 이미 사용된 카드인지 확인
* showCardStatus : 카드 분배 상태를 보여줌, 딜러인 경우 처음 1개만

### 카드 추가 ###
* askAddCard : 카드를 추가할지 묻기
* isValidYN : 유효한 대답인지(y/n) 확인 
* addCard : 히트선택 시 카드 얻기
* addCardForDealer : 딜러는 게임조건에 따라 알아서 카드를 추가하게 하기

### 결과 출력 ###
* showResultValue : 카드 최종 결과 보여주기 
* calcResultValue : 카드 합 계산하기. 버스트는 0, 21인 경우는 카드 갯수가 2개인 경우 22, 그 외의 경우는 합 그대로 계산
* showResultMoney : 결과적으로 얻는 금액 표시하기
* isPush : 블랙잭으로 승리한 유저가 있을 경우 딜러도 블랙잭인지 확인

