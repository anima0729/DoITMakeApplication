package com.cookandroid.bottom_setting;

public class Another_Notice_Data {

    String notice;
    String name;
    String date;
    String contents;

    public Another_Notice_Data() {}

    // 알림부분에 넣을 데이터 받아오기
    public Another_Notice_Data(String notice, String name, String date, String contents) {
        this.notice = notice;
        this.name = name;
        this.date = date;
        this.contents = contents;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getContents() {
        return contents;
    }

    public void setContents(String date) {
        this.contents = contents;
    }

}
