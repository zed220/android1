package com.example.lesson1;

import java.util.Random;

public final class CalculationProcessor {
    static Random Randomizer = new Random(System.currentTimeMillis() % 1000);

    private CalculationProcessor() {
    }

    public static CalculationResult Calculate(GameUnitStatus attacking, GameUnitStatus defending) {
        //attacking.Distance
        //attacking.Health
        AttackingInfo attackingInfo = attacking.GetAttackingInfo(defending.GetUnitType());
        for(int i = 0; i < attackingInfo.DiceCount; i++){
            int dice = Randomizer.nextInt(6) + 1;

        }

        return new CalculationResult(isPanic, decArmor, decHealth);
    }
}
