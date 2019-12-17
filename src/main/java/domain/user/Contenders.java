package domain.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import domain.game.Deck;

public class Contenders implements Iterable<Contender> {
    private List<Contender> contenders;

    public Contenders() {
        this.contenders = new ArrayList<>();
    }

    public void add(Contender contender) {
        contenders.add(contender);
    }

    public void eachDrawsPair(Deck deck) {
        for (Contender contender : contenders) {
            contender.drawPairFrom(deck);
        }
    }

    public void printResult() {
        for (Contender contender : contenders) {
            System.out.println(contender + " - 결과: " + contender.getFinalSum());
        }
    }

    public Stream<Contender> getStream() {
        return contenders.stream().filter(Contender::notBusted).sorted();
    }

    @Override
    public Iterator<Contender> iterator() {
        return contenders.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contender contender : contenders) {
            sb.append(contender).append("\n");
        }
        return sb.toString();
    }
}
