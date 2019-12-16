package domain.outcome;

public class Outcome {
    private final String name;
    private double benefit;

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

    public void setDealerBenefit(double benefit) {
        if (isDealer() == false) {
            throw new IllegalStateException("Dealer가 아니면 benefit을 변경할 수 없습니다.");
        }
        this.benefit = benefit;
    }

    public boolean isNameMatch(String name) {
        return this.name.equals(name);
    }

    public boolean isDealer() {
        return this.name.equals("Dealer");
    }
}
