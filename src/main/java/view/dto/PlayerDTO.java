package view.dto;

import domain.user.Player;

public class PlayerDTO {
    private final String name;
    private final Double money;

    public PlayerDTO(String name, Double money) {
        this.name = name;
        this.money = money;
    }

    public Player toEntity() {
        return new Player(this.name, this.money);
    }
}
