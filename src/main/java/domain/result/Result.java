package domain.result;

public enum Result {
    COMPLETE(1.5),
    GET(1),
    LOOSE(-1),
    DRAW(0);

    private double weight;

    Result(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
