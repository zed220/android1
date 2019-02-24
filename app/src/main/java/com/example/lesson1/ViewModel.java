package com.example.lesson1;
import java.util.*;

public final class ViewModel {
    private Vector<GameUnit> Units;
    private GameUnitStatus AttackingUnitStatus;
    private GameUnitStatus DefendingUnitStatus;

    final List<OnChangeGameUnitListener> OnChangeAttackingGameUnitListeners = new Vector<OnChangeGameUnitListener>();
    final List<OnChangeGameUnitListener> OnChangeDefendingGameUnitListeners = new Vector<OnChangeGameUnitListener>();

    public List<GameUnit> GetUnits() { return Units; }

    public GameUnit GetAttackingUnit() { return AttackingUnitStatus.GetUnit(); }
    public void SetAttackingUnit(GameUnit value) {
        AttackingUnitStatus = new GameUnitStatus(value);
        for(OnChangeGameUnitListener listener : OnChangeAttackingGameUnitListeners)
            listener.UnitChanged(AttackingUnitStatus);
    }

    public GameUnit GetDefendingUnit() { return DefendingUnitStatus.GetUnit(); }
    public void SetDefendingUnit(GameUnit value) {
        DefendingUnitStatus = new GameUnitStatus(value);
        for(OnChangeGameUnitListener listener : OnChangeDefendingGameUnitListeners)
            listener.UnitChanged(DefendingUnitStatus);
    }

    public void AddOnChangeAttackingGameUnitListener(OnChangeGameUnitListener listener) {
        OnChangeAttackingGameUnitListeners.add(listener);
    }
    public void OnChangeDefendingGameUnitListener(OnChangeGameUnitListener listener) {
        OnChangeDefendingGameUnitListeners.add(listener);
    }

    public void Clear(){
        SetAttackingUnit(null);
        SetDefendingUnit(null);
    }

    public void Calculate() {

    }
}