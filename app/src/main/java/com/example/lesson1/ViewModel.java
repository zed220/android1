package com.example.lesson1;

import android.content.res.AssetManager;
import android.content.res.Resources;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.*;

public final class ViewModel {
    private final Vector<GameUnit> Units;
    private GameUnitStatus AttackingUnitStatus;
    private GameUnitStatus DefendingUnitStatus;

    private final List<OnChangeGameUnitListener> OnChangeAttackingGameUnitListeners = new Vector<OnChangeGameUnitListener>();
    private final List<OnChangeGameUnitListener> OnChangeDefendingGameUnitListeners = new Vector<OnChangeGameUnitListener>();

    public ViewModel(Resources resources) throws IOException, XmlPullParserException {
        Units = GameUnitsLoader.GetAllUnits(resources);
        Clear();
    }

    public List<GameUnit> GetUnits() { return Units; }

    public GameUnit GetAttackingUnit() { return AttackingUnitStatus.GetUnit(); }

    public GameUnitStatus GetAttackingUnitStatus() { return AttackingUnitStatus; }
    public GameUnitStatus getDefendingUnitStatus() { return DefendingUnitStatus; }

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
        SetAttackingUnit(GetUnits().get(0));
        SetDefendingUnit(GetUnits().get(0));
    }

    public CalculationResult Calculate() {
        return CalculationProcessor.Calculate(GetAttackingUnitStatus(), getDefendingUnitStatus());
    }
    public boolean IsAttackingUnitInPanic() {
        return CalculationProcessor.IsPanic(GetAttackingUnitStatus(), GetAttackingUnitStatus().Health);
    }
}