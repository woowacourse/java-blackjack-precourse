블랙잭 게임 프로그램   

블랙잭 : 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 쪽이 이기는 게임  

1. main 조립기 영역에서 플레이어 리스트 객체 생성. 
    - 쉼표를 기준으로 플레이어 이름, 각 플레이어들의 배팅 금액 입력받는다.   
    - 아직 콘솔로 입력 받는 로직은 구현 안함  

2. 전체 카드 목록 중 중복 허용 안하고 카드 고르는 기능 추가.      
3. 모든 플레이어들한테 카드 두장씩 분배한다.   
4. 딜러 두번째 카드,  참가자 모든 카드 목록 출력한다. 
5. 플레이어별 카드를 더 받을지 체크한다.  
       -일반 플레이어 : y/n 입력   
       -딜러 : 카드 2개 && 합 16이하  
6. 점수 계산 기능 추가.  
-  블랙잭 여부 체크한다.  
     - 딜러 블랙잭인 경우 : 참가자 블랙잭이면. 이득 없다, 아니면 배팅금액 다 잃어.
     - 딜러 블랙잭 아닌 경우 : 참가자 블랙잭이면 배팅 1.5배로 무조건 이긴다!
-  블랙잭 아닌 경우.     
-  딜러는 두 카드 합이 16이하이면 반드시 1장의 카드를 추가로 받아야한다.   
-  플레이어가 카드 추가로 받은뒤 21 넘어가면 무조건 패. 배팅금 잃는다. 
-  딜러가 21 초과하면 남아있는 플레이어들은 무조건 승리한다.  
