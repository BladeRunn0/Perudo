package com.epf.perudoback.models;

import jakarta.persistence.Entity;

import java.io.Serializable;

public enum DiceValue implements Serializable {
    PACO, DEUX, TROIS, QUATRE, CINQ, SIX;

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

    public static DiceValue getFromNumber(int n) {
        switch (n) {
            case 1:
                return DiceValue.PACO;
            case 2:
                return DiceValue.DEUX;
            case 3:
                return DiceValue.TROIS;
            case 4:
                return DiceValue.QUATRE;
            case 5:
                return DiceValue.CINQ;
            default:
                return DiceValue.SIX;
        }
    }

    public static int getStringDiceValue(String face) {
        switch (face) {
            case "PACO":
                return 1;
            case "DEUX":
                return 2;
            case "TROIS":
                return 3;
            case "QUATRE":
                return 4;
            case "CINQ":
                return 5;
            case "SIX":
                return 6;
            default:
                return 0;
        }
    }

    public static String getStringHigherFace(String face) {
        switch (face) {
            case "PACO":
                return "DEUX";
            case "DEUX":
                return "TROIS";
            case "TROIS":
                return "QUATRE";
            case "QUATRE":
                return "CINQ";
            case "CINQ":
                return "SIX";
            default:
                return "SIX";
        }
    }
}
