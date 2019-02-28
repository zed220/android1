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
        XmlPullParser parser1 = resources.getXml(R.xml.units);
        @SuppressLint("ResourceType") InputStream in = resources.openRawResource(R.xml.units);
        try {
            XmlPullParser parser = resources.getXml(R.xml.units);
            //parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            //parser.setInput(in, "utf8");
            //parser.nextTag();
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                //if (name.equals("title")) {
                //    title = readTitle(parser);
                //} else if (name.equals("summary")) {
                //    summary = readSummary(parser);
                //} else if (name.equals("link")) {
                //    link = readLink(parser);
                //} else {
                //    skip(parser);
                //}
            }
        } finally {
            in.close();
        }
        return result;
    }
}
