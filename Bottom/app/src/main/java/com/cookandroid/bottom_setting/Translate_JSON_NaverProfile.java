package com.cookandroid.bottom_setting;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Translate_JSON_NaverProfile {

    JSONObject Object;
    String info;
    JSONObject cleanObject;
    String ID;
    String Gender;
    String Nickname;
    Integer Level;
    Double Exp;

    Translate_JSON_NaverProfile(String a) throws JSONException {
        try {
            this.Object = new JSONObject(a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        info = Object.getString("naver_profile");

        info = info.replace("[", "");
        info = info.replace("]", "");
        cleanObject = new JSONObject(info);
        Exp = cleanObject.getDouble("exp");
        Level = cleanObject.getInt("level");
        ID = cleanObject.getString("id");
        Gender = cleanObject.getString("gender");
        Nickname = cleanObject.getString("nickname");
    }

    public Double getExp() {

        return Exp;
    }

    public String getGender() {

        return Gender;
    }

    public Integer getLevel() {

        return Level;
    }

    public String getID() {

        return ID;
    }

    public String getNickname() {

        return Nickname;
    }
}
