package domain.game;

import domain.user.*;
import domain.ui.Utill;

public class Game {
    public static void main(String[] args){
        initgame();
    }

    private static void initgame(){
        Utill utill = new Utill();
        utill.initPlayerNameList();
    }
}
