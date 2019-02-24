package com.example.lesson1;

import java.util.*;

public final class GameUnit {
    public final GameUnitType Type;
    private final int DefaultArmor;
    private final Dictionary<GameUnitType, List<Integer>> AttackInfoList;
    final Vector<Integer> Distances;
    private final GameUnitMoralInfo MoralInfo;

    public GameUnit(GameUnitType type, int defaultArmor, Dictionary<GameUnitType, List<Integer>> attackInfos, Vector<Integer> distances, GameUnitMoralInfo moralInfo) {
        Type = type;
        DefaultArmor = defaultArmor;
        AttackInfoList = attackInfos;
        Distances = distances;
        MoralInfo = moralInfo;
    }

    public int GetAttackInfo(int health, GameUnitType defendingUnitType) { return AttackInfoList.get(defendingUnitType).get(health); }
}