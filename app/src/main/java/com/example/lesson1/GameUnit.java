package com.example.lesson1;

import java.util.Vector;

public final class GameUnit {
    private final GameUnitType Type;
    private final int DefaultArmor;
    private final Vector<AttackInfo> AttackInfoList;
    final Vector<Integer> Distances;
    private final GameUnitMoralInfo MoralInfo;

    public GameUnit(GameUnitType type, int defaultArmor, Vector<AttackInfo> attackInfos, Vector<Integer> distances, GameUnitMoralInfo moralInfo) {
        Type = type;
        DefaultArmor = defaultArmor;
        AttackInfoList = attackInfos;
        Distances = distances;
        MoralInfo = moralInfo;
    }

    public AttackInfo GetAttackInfo(int health) { return AttackInfoList.elementAt(health); }
}