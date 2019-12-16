package domain.outcome;

public class Outcome {
    private final String name;
    private final double benefit;

    public Outcome(String name, double benefit) {
        this.name = name;
        this.benefit = benefit;
    }

    public void printOutcome() {
        System.out.println(name + " : " + benefit);
    }

    public String getName() {
        return name;
    }

    public double getBenefit() {
        return benefit;
    }

    public boolean isNameMatch(String name) {
        return this.name.equals(name);
    }
}
