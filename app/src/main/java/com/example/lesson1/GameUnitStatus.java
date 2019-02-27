package com.example.lesson1;

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

    public AttackingInfo GetAttackingInfo(GameUnitType defendingType) {
        return new AttackingInfo(Unit.GetAttackInfo(Health, defendingType), Unit.Distances.get(Distance));
    }
    public GameUnitType GetUnitType(){ return Unit.Type; }
    public GameUnitMoralInfo GetMoral() { return Unit.MoralInfo; }
    public int GetMaxHealth() { return Unit.Health; }
}
