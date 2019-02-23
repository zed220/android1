package com.example.lesson1;
import java.util.List;
import java.util.Vector;

public final class ViewModel {
    private Vector<GameUnit> Units;
    private GameUnitStatus AttackingUnitStatus;
    private GameUnitStatus DefendingUnitStatus;

    public List<GameUnit> GetUnits() { return Units; }

    public GameUnit GetAttackingUnit() { return AttackingUnitStatus.GetUnit(); }
    public void SetAttackingUnit(GameUnit value) { AttackingUnitStatus = new GameUnitStatus(value); }

    public GameUnit GetDefendingUnit() { return AttackingUnit; }
    public void SetDefendingUnit(GameUnit value) { DefendingUnit = value; }

    public void Clear(){
        SetAttackingUnit(null);
        SetDefendingUnit(null);
    }
}