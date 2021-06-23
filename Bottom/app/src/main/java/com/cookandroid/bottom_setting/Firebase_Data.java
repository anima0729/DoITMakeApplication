package com.cookandroid.bottom_setting;

public class Firebase_Data {
    public String category;
    public String degre;
    public String detail;
    public String end_term;
    public String id;
    public String level;
    public String start_term;
    public String title;
    public String start_time;
    public String end_time;

    public  Firebase_Data(){
        this.category="";
        this.degre="";
        this.detail="";
        this.end_term="";
        this.id="";
        this.level="";
        this.start_term="";
        this.title="";
        this.start_time="";
        this.end_time="";
    }

    public Firebase_Data(String category, String degree, String detail, String end_term,String end_time,
                              String id, String level, String start_term, String start_time, String title){
        this.category=category;
        this.degre=degree;
        this.detail=detail;
        this.end_term=end_term;
        this.end_time=end_time;
        this.id=id;
        this.level=level;
        this.start_term=start_term;
        this.start_time=start_time;
        this.title=title;
    }
}