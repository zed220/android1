package com.example.lesson1;

import java.util.List;
import java.util.Vector;

public final class GameUnitStatus {
    private final GameUnit Unit;

    public int Health = 1;
    public int Armor = 1;
    public int Distance = 1;
    public boolean SuppressFire;

    public GameUnitStatus(GameUnit unit) {
        Unit = unit;
    }

    public GameUnit GetUnit() { return Unit; }


}
