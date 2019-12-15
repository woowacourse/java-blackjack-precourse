package domain.user;

public enum Will {
    Hit("y"),
    Stay("n");

    private String value;

    Will(String value) {
        this.value = value;
    }

}
