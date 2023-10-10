package com.epf.perudoback.models;

import java.io.Serializable;

public enum Dice implements Serializable {
    PACO, DEUX, TROIS, QUATRE, CINQ, SIX;

    public static boolean bigger(Dice a, Dice b) {
        return a.ord() > b.ord();
    }

    private int ord() {
        switch (this) {
            case PACO:
                return 1;
            case DEUX:
                return 2;
            case TROIS:
                return 3;
            case QUATRE:
                return 4;
            case CINQ:
                return 5;
            default:
                return 6;
        }
    }

    public static Dice getFromNumber(int n) {
        switch (n) {
            case 1:
                return Dice.PACO;
            case 2:
                return Dice.DEUX;
            case 3:
                return Dice.TROIS;
            case 4:
                return Dice.QUATRE;
            case 5:
                return Dice.CINQ;
            default:
                return Dice.SIX;
        }
    }
}
