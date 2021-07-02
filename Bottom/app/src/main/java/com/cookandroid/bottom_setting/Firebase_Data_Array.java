package com.cookandroid.bottom_setting;

import android.graphics.drawable.Drawable;

public class Firebase_Data_Array {
    public String degree;
    public String detail;
    public String end_term;
    public String start_term;
    public String title;

    public Firebase_Data_Array(){
        this.degree="";
        this.detail="";
        this.end_term="";
        this.start_term="";
        this.title="";
    }

    public Firebase_Data_Array( String detail, String end_term, String degree, String start_term, String title){
        this.detail=detail;
        this.end_term=end_term;
        this.start_term=start_term;
        this.title=title;
        this.degree=degree;
    }

    public void setGoal(String goal) { title = title; }
    public void setSdate(String sdate) {
        start_term = start_term;
    }
    public void setEdate(String edate) {
        end_term = end_term;
    }
    public void setEtc(String detail) {
        detail = detail;
    }
    public void setPer(String degree) { degree = degree;}


    public String getGoal() { return this.title; }
    public String getSdate() {
        return this.start_term;
    }
    public String getEdate() { return this.end_term; }
    public String getEtc() {
        return this.detail;
    }
    public String getPer() {
        return this.degree;
    }
}