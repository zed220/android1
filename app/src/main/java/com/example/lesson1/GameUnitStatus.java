package com.example.lesson1;

public final class GameUnitStatus {
    private final GameUnit Unit;

    public int Health = 1;
    public int Distance = 1;

    public GameUnitStatus(GameUnit unit) {
        Unit = unit;
    }

    public GameUnit GetUnit() { return Unit; }


}
