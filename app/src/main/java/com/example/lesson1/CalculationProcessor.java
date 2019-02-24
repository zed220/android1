package com.example.lesson1;

import java.util.Random;

public final class CalculationProcessor {
    static Random Randomizer = new Random(System.currentTimeMillis() % 1000);

    private CalculationProcessor() {
    }

    public static CalculationResult Calculate(GameUnitStatus attacking, GameUnitStatus defending) {
        //attacking.Distance
        //attacking.Health
        return new CalculationResult();
    }
}
