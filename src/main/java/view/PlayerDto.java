package view;

import domain.user.Player;

public class PlayerDto {
    private final String name;
    private final Double money;

    public PlayerDto(String name, Double money) {
        this.name = name;
        this.money = money;
    }

    public Player toEntity() {
        return new Player(this.name, this.money);
    }
}
