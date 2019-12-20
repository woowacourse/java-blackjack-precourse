package domain.outcome;

public class Outcome {
    private final String name;
    private final Benefit benefit;

    Outcome(String name, double benefit) {
        this.name = name;
        this.benefit = new Benefit((int) benefit);
    }

    public String getName() {
        return name;
    }

    public double getBenefit() {
        return benefit.getBenefit();
    }

    boolean isNameMatch(String name) {
        return this.name.equals(name);
    }
}
