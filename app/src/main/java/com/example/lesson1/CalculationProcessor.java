package com.example.lesson1;

import java.util.Random;

public final class CalculationProcessor {
    private static Random Randomizer = new Random(System.currentTimeMillis() % 1000);

    private CalculationProcessor() {
    }

    public static CalculationResult Calculate(GameUnitStatus attacking, GameUnitStatus defending) {
        AttackingInfo attackingInfo = attacking.GetAttackingInfo(defending.GetUnitType());
        int decHealth = 0;
        int decArmor = 0;
        boolean isPanic = false;
        int diceCount = attacking.SuppressFire ? (int)Math.floor(attackingInfo.DiceCount * 1.5) : attackingInfo.DiceCount;
        for(int i = 0; i < diceCount; i++){
            int diceResult = Randomizer.nextInt(6) + 1;
            if(diceResult > attackingInfo.MinDiceValue)
                continue;
            if(defending.Armor > decArmor) {
                decArmor++;
                continue;
            }
            decHealth++;
        }
        if(attacking.SuppressFire || decHealth > 0) {
            GameUnitMoralInfo moralInfo = attacking.GetMoral();
            int diceSum = 0;
            for(int i = 0; i < moralInfo.DiceCount; i++) {
                diceSum += Randomizer.nextInt(6) + 1;
            }
            isPanic = diceSum > (moralInfo.Maximum - (defending.GetMaxHealth() - defending.Health - decHealth));
        }
        return new CalculationResult(isPanic, defending.Armor, decArmor, defending.Health, decHealth);
    }
}
