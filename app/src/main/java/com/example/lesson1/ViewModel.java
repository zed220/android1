package com.example.lesson1;
import java.util.List;
import java.util.Vector;

public final class ViewModel {
    private Vector<GameUnit> Units;
    private GameUnit AttackingUnit;
    private GameUnit DefendingUnit;

    public List<GameUnit> GetUnits() { return Units; }

    public GameUnit GetAttackingUnit() { return AttackingUnit; }
    public void SetAttackingUnit(GameUnit value) { AttackingUnit = value; }

    public GameUnit GetDefendingUnit() { return AttackingUnit; }
    public void SetDefendingUnit(GameUnit value) { DefendingUnit = value; }

    public void Clear(){
        SetAttackingUnit(null);
        SetDefendingUnit(null);
    }
}
