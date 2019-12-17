<img src="https://woowacourse.github.io/images/logo/logo_full_dark.png"/>
<h1>우아한 테크코스 프리코스</h1>
<h2>3주차 미션 - 블랙잭</h2>
<h2>제작 : 황보경(https://github.com/hwangbo)</h2>

<h3>실행 방법</h3>
<span>Blackjack.java에서 컴파일.</span>

<h3>기능 요구사항</h3>
<ol>
    <ul>블랙 잭 게임을 진행하는 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.</ul>
    <ul>플레이어는 게임을 시작할 때 베팅 금액을 정해야 한다. 카드의 숫자 계산은 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.</ul>
    <ul>게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.</ul>
    <ul>처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.</ul>
    <ul>딜러는 처음에 받은 2장의 합계가 16 이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.</ul>
</ol>
<h3>기능 구현 목록</h3>
<ol>
    <li>
        Blackjack.java
        <ul>
            <li>메인 함수가 담겨있는 클래스.</li>
            <li>카드덱과 플레이어를 생성하는 startGame()</li>
            <li>처음 2장을 주는 firstDraw()</li>
            <li>처음 2장이 블랙잭인지 확인하는 isBlackjack()</li>
            <li>처음 2장이 블랙잭일 경우의 출력할 내용을 저장하는 setIsFinish()</li>
            <li>플레이어 배열을 생성하는 participatePlayers()</li>
            <li>게임의 비지니스 업무를 담당하는 gameSet()</li>
            <li>패를 받는 doDraw()</li>
            <li>승패를 확인하는 checkWinner()</li>
            <li>딜러의 패가 16이하일 경우 카드를 더 주는 checkDealer()</li>
            <li>각 유저들의 결과를 출력하는 showResult()</li>
            <li>수입을 알려주는 showWinner()</li>
        </ul>
    </li>
    <li>
        Initialize.java
        <ul>
            <li>각 입력을 받는 클래스</li>
            <li>이름을 입력받는 setPlayerNames()</li>
            <li>배팅 금액을 입력받는 setPlayerBettings()</li>
        </ul>
    </li>
    
</ol>
