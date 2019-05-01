package com.example.lesson1;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public final class GameUnitsLoader {
    private GameUnitsLoader(){}

    public static Vector<GameUnit> GetAllUnits(Resources resources) throws IOException, XmlPullParserException {
        Vector<GameUnit> result = new Vector<>();

        XmlPullParser parser = resources.getXml(R.xml.units);
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            if(tagName.equals("unit"))
                result.add(ParseUnit(parser));
        }
        return result;
    }

    private static GameUnit ParseUnit(XmlPullParser parser) throws IOException, XmlPullParserException {
        String name = null;
        GameUnitType type = GameUnitType.INFANTRY;
        int armor = -1;
        int[] distances = null;
        int moralTotal = -1;
        int moralDec = -1;
        int moralDice = -1;
        Hashtable<GameUnitType, int[]> attacks = null;
        for(int i = 0; i < parser.getAttributeCount(); i++){
            switch (parser.getAttributeName(i)){
                case "name": name = parser.getAttributeValue(i);
                    break;
                case "type": type = GameUnitType.valueOf(parser.getAttributeValue(i));
                    break;
                case "armor": armor = Integer.valueOf(parser.getAttributeValue(i));
                    break;
                case "distances": distances = GetIntArr(parser.getAttributeValue(i));
                    break;
                case "moralTotal": moralTotal = Integer.valueOf(parser.getAttributeValue(i));
                    break;
                case "moralDec": moralDec = Integer.valueOf(parser.getAttributeValue(i));
                    break;
                case "moralDice": moralDice = Integer.valueOf(parser.getAttributeValue(i));
                    break;
            }
        }
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            if(tagName.equals("attackList"))
                attacks = GetAttacks(parser);
        }
        return new GameUnit(name, type, armor, attacks, distances, new GameUnitMoralInfo(moralTotal, moralDec, moralDice), false);
    }
    private static void ReverseArray(int[] validData){
        for(int i = 0; i < validData.length / 2; i++) {
            int temp = validData[i];
            validData[i] = validData[validData.length - i - 1];
            validData[validData.length - i - 1] = temp;
        }
    }

    private static Hashtable<GameUnitType, int[]> GetAttacks(XmlPullParser parser) throws IOException, XmlPullParserException {
        Hashtable<GameUnitType, int[]> result = new Hashtable<>();
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if(parser.getEventType() == XmlPullParser.END_TAG){
                if(parser.getName().equals("attackList"))
                    return  result;
            }

            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            if(!tagName.equals("attackInfo"))
                continue;
            GameUnitType type = GameUnitType.INFANTRY;
            int[] dices = null;
            for(int i = 0; i < parser.getAttributeCount(); i++){
                switch (parser.getAttributeName(i)){
                    case "type": type = GameUnitType.valueOf(parser.getAttributeValue(i));
                        break;
                    case "values": dices = GetIntArr(parser.getAttributeValue(i));
                        ReverseArray(dices);
                        break;
                }
            }
            result.put(type, dices);
        }

        return result;
    }

    static int[] GetIntArr(String source) {
        String[] integersAsText = source.split(",");
        int[] results = new int[ integersAsText.length ];
        int i = 0;
        for(String textValue : integersAsText) {
            results[i] = Integer.parseInt(textValue);
            i++;
        }
        return results;
    }
}
