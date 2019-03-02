package com.example.lesson1;

import java.util.*;

public final class GameUnit {
    private final String Name;
    public final GameUnitType Type;
    public final int Armor;
    private final Hashtable<GameUnitType, int[]> AttackInfoList;
    final int[] Distances;
    final GameUnitMoralInfo MoralInfo;
    final int Health;

    public GameUnit(String name, GameUnitType type, int defaultArmor, Hashtable<GameUnitType, int[]> attackInfos, int[] distances, GameUnitMoralInfo moralInfo) {
        Name = name;
        Type = type;
        Armor = defaultArmor;
        AttackInfoList = attackInfos;
        Distances = distances;
        MoralInfo = moralInfo;
        Health = AttackInfoList.get(GameUnitType.INFANTRY).length;
    }

    public String toString(){
        return Name;
    }

    public int GetAttackInfo(int health, GameUnitType defendingUnitType) { return AttackInfoList.get(defendingUnitType)[health]; }
}