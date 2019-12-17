package domain;

import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;
import domain.card.Card;
import domain.card.CardFactory;
import java.util.*;
public class Blackjack {
    Scanner scanner = new Scanner(System.in);
    String[] Names;
    //    Card[] cards;
    double[] Betting = new double[10];
    int i,num,j,k;
    int head;
    List<Card> cardlist = CardFactory.create();
    List<Integer> card = new ArrayList<>();
    List<Gamer> player = new ArrayList<>();

    public void game(){
        String Name;
        System.out.println("Input Gamer Name");
        Name = scanner.nextLine();
        Names = Name.split(",");
        for(i=0; i<Names.length; i++){
            System.out.println(Names[i] + "  betting Money?");
            Betting[i] = scanner.nextDouble();
        }
        setPlayer();
        Shuffle();
        Gamestart();
        Game();
    }
    public void Shuffle(){
        for(i=0; i<cardlist.size(); i++){
            card.add(i);
        }
        Collections.shuffle(card);
    }

    public void setPlayer(){
        player.add(new Dealer());
        for(i=0; i<Names.length; i++){
            player.add(create(Names[i],Betting[i]));
        }
    }
    public Player create(String name, double bet){
        Player player_tmp = new Player(name,bet);
        return player_tmp;
    }
    public void Gamestart() {
        System.out.println("Dealer gives 2 cards to each player");
        for(Gamer p : player) {
            p.addCard(cardlist.get(card.get(head++)));
            p.addCard(cardlist.get(card.get(head++)));
            printfirst(p);
        }
    }
    /*   public void printdealer(Dealer gamer) {
           System.out.print("Dealer : ");
           for (Card c : gamer.getCards()){
               System.out.print(c.getSymbol().getScore() + " " + c.getType().name() + " ");
           }
       }*/
    public void printfirst(Gamer gamer) {
        if (gamer.getClass() == Dealer.class) {
            System.out.print("\nDealer : ");
            int cnt =0 ;
            for (Card c : gamer.getCards()){
                cnt ++;
                System.out.print(c.getSymbol().getScore() + " " + c.getType().name() + " ");
                if(cnt == 1) break;
            }
        }
        if (gamer.getClass() == Player.class) {
            Player player = (Player) gamer;
            System.out.print("\n" + player.getName() + " : ");
            for (Card c : gamer.getCards())
                System.out.print(c.getSymbol().getScore() + " " + c.getType().name() + " ");
        }
    }
    public void printplayer(Gamer gamer) {
        if (gamer.getClass() == Dealer.class) {
            System.out.print("\nDealer : ");
            for (Card c : gamer.getCards()){
                System.out.print(c.getSymbol().getScore() + " " + c.getType().name() + " ");
            }
        }
        if (gamer.getClass() == Player.class) {
            Player player = (Player) gamer;
            System.out.print("\n" + player.getName() + " : ");
            for (Card c : gamer.getCards())
                System.out.print(c.getSymbol().getScore() + " " + c.getType().name() + " ");
        }
    }
    public void Game(){
        List<Gamer> win = new ArrayList<>();
        win = blackjack();
        if (win.isEmpty()) {
            playerTurn();
            dealerTurn();
            endGame();
            winner();
        }
        if(!win.isEmpty()) WinBJ(win);
    }
    public void endGame() {
        for (Gamer g : player) {
            printplayer(g);
            System.out.println(result(g));
        }
    }
    public List<Gamer> blackjack(){
        List<Gamer> win = new ArrayList<>();
        for(Gamer p : player){
            if(result(p) == 21){
                win.add(p);
            }
        }
        return win;
    }
    public int result(Gamer player){
        int result = sum(player);
        if(player.hasACE() && result + 10 <= 21){
            result = result + 10;
        }
        return result;
    }
    /*   public int result(Dealer dealer){
           int result = sumd(dealer);
           if(dealer.hasACE() && result + 10 <= 21){
               result = result + 10;
           }
           return result;
       }*/
 /*   public int sump(Player player){
        int sum=0;
        for(Card c : player.getCards()){
            sum += c.getSymbol().getScore();
        }
        return sum;
    }*/
    public int sum(Gamer play){
        int sum=0;
        for(Card c : play.getCards()){
            sum += c.getSymbol().getScore();
        }
        return sum;
    }
    public void winck1(List<Gamer> winner){
        if (!(winner.isEmpty()) && winner.size() == 1) {
            if (winner.get(0).getClass() == Dealer.class)
                dealerWin();
        }
    }
    public void winck2(List<Gamer> winner){
        if (!(winner.isEmpty()) && winner.size() != 1) {
            if (winner.get(0).getClass() == Dealer.class)
                draw(winner);
        }
    }
    public void winck3(List<Gamer> winner){
        if (!(winner.isEmpty())) {
            if (winner.get(0).getClass()!=Dealer.class)
                playerWin(winner);
        }
    }
    public void winner() {
        List<Gamer> winner;
        winner = blackjack();
        if (winner.isEmpty())
            winner = EndBJ();
        winck1(winner);
        winck2(winner);
        winck3(winner);
    }
    public boolean ck1(List<Gamer> winner, boolean check, Gamer g){
        if (winner.isEmpty() && result(g) <= 21 && check) {
            winner.add(g);
            return false;
        }
        return check;
    }
    public boolean ck2(List<Gamer> winner, boolean check, Gamer g){
        if (!(winner.isEmpty()) && result(g) > result(winner.get(0)) && result(g) <=21  && check) {
            winner.clear();
            winner.add(g);
            return false;
        }
        return check;
    }
    public boolean ck3(List<Gamer> winner, boolean check, Gamer g){
        if (!(winner.isEmpty()) && result(g) == result(winner.get(0)) && check) {
            winner.add(g);
            return false;
        }
        return check;
    }
    public void ck(List<Gamer> winner){
        for (Gamer g : player) {
            boolean check = true;
            check = ck1(winner,check,g);
            check = ck2(winner,check,g);
            check = ck3(winner,check,g);
        }
    }
    public List<Gamer> EndBJ() {
        List<Gamer> winner = new ArrayList<>();
        if (result(player.get(0)) > 21) {
            for (int i = 1 ; i < player.size(); i++) {
                winner.add(player.get(i));
            }
            return winner;
        }
        ck(winner);
        return winner;
    }
    public void playerTurn() {
        for (int i = 1; i < player.size(); i++) {
            turn(player.get(i));
        }
    }
    public void turn(Gamer gamer) {
        boolean morecard = true;
        while (sum(gamer) < 21 && morecard) {
            Player p = (Player) gamer;
            System.out.println("\n" + p.getName() + "One more card? y or n");
            char answer = scanner.next().charAt(0);
            if ('y' == answer) {
                gamer.addCard(cardlist.get(card.get(head++)));
                printplayer(gamer);
            }
            if ('n' == answer) {
                morecard = false;
                printplayer(gamer);
            }
        }
    }
    public void dealerTurn() {
        if (sum(player.get(0)) <= 16) {
            System.out.println("\nDealer get one more card.");
            player.get(0).addCard(cardlist.get(card.get(head++)));
        }
    }
    public void playerWin(List<Gamer> winner) {
        System.out.println("\nFinal Stats Player Win");
        System.out.println("Dealer : 0");
        for (Gamer g : winner) {
            Player p = (Player) g;
            p.win();
        }
        for (int i = 1; i <player.size(); i++) {
            Player p = (Player) player.get(i);
            System.out.println(p.getName() + " : " + p.getStat());
        }
    }
    public void dealerWin() {
        double sum = 0;
        System.out.println("\nFinal Stats Dealers Win");
        for (int i = 1; i < player.size(); i++) {
            Player p = (Player) player.get(i);
            sum = sum - p.getStat();
        }
        System.out.print("Dealer : " + sum);
        System.out.println("");
        for (int i = 1; i < player.size(); i++) {
            Player p = (Player) player.get(i);
            System.out.println(p.getName() + " : " + p.getStat());
        }
    }
    public void draw(List<Gamer> winner) {
        System.out.println("\nFinal Stats Draw");
        for (i = 1; i < winner.size(); i++) {
            Player p = (Player) winner.get(i);
            p.draw();
        }
        double sum = 0;
        for (i = 1; i < player.size(); i++) {
            Player p = (Player) player.get(i);
            sum = sum - p.getStat();
        }
        System.out.println("Dealer : " + sum);
        for (i = 1; i < player.size(); i++) {
            Player p = (Player) player.get(i);
            System.out.println(p.getName() + " : " + p.getStat());
        }
    }
    public void WinBJ(List<Gamer> winner) {
        System.out.println("BLACK JACK");
        if (winner.get(0) != player.get(0)) {
            double sum = 0;
            System.out.println("\nFinal stats");
            for (int i = 0; i < winner.size(); i++) {
                Player p = (Player) player.get(i);
                p.blackjack();
                sum = sum - p.getStat();
            }
            System.out.print("Dealer : " + sum);
            System.out.println("");
            for (int i = 1; i < player.size(); i++) {
                Player p = (Player) player.get(i);
                System.out.println(p.getName() + " : " + p.getStat());
            }
        }
        if (winner.get(0) != player.get(0) && winner.size() == 1) {
            dealerWin();
        }
        if (winner.get(0) != player.get(0) && winner.size() != 1) {
            draw(winner);
        }
    }
}
