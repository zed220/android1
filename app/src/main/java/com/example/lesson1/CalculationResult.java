package com.example.lesson1;

public final class CalculationResult {
    public final boolean IsPanic;
    public final int Armor;
    public final int DecArmor;
    public final int Health;
    public final int DecHealth;

    public CalculationResult(boolean isPanic, int armor, int decArmor, int health, int decHealth) {
        IsPanic = isPanic;
        Armor = armor;
        DecArmor = decArmor;
        Health = health;
        DecHealth = decHealth;
    }
}
