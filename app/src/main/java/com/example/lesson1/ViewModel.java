package com.example.lesson1;

import java.util.*;

public final class ViewModel {
    private final Vector<GameUnit> Units;
    private GameUnitStatus AttackingUnitStatus;
    private GameUnitStatus DefendingUnitStatus;

    private final List<OnChangeGameUnitListener> OnChangeAttackingGameUnitListeners = new Vector<OnChangeGameUnitListener>();
    private final List<OnChangeGameUnitListener> OnChangeDefendingGameUnitListeners = new Vector<OnChangeGameUnitListener>();

    public ViewModel(){
        Units = new Vector<GameUnit>();
        Hashtable<GameUnitType, int[]> distances = new Hashtable<>();
        distances.put(GameUnitType.INFANTRY, new int[] { 9, 8, 7, 6, 5 });
        Units.add(new GameUnit("Советская пехота", GameUnitType.INFANTRY, 1, distances, new Vector<Integer>(), new GameUnitMoralInfo(8, 1)));
    }

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
    public void AddOnChangeDefendingGameUnitListener(OnChangeGameUnitListener listener) {
        OnChangeDefendingGameUnitListeners.add(listener);
    }

    public void Clear(){
        SetAttackingUnit(null);
        SetDefendingUnit(null);
    }

    public void Calculate() {

    }
}