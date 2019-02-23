package com.example.lesson1;

public final class GameUnitMoralInfo {
    final int Maximum;
    final int Decrement;
    final int DiceCount;

    public GameUnitMoralInfo(int maximum, int decrement, int diceCount) {
        Maximum = maximum;
        Decrement = decrement;
        DiceCount = diceCount;
    }
    public GameUnitMoralInfo(int maximum, int decrement) {
        this(maximum, decrement, 2);
    }
}
