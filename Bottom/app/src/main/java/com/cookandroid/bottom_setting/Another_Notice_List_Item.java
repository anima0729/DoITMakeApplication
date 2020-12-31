package com.cookandroid.bottom_setting;

// 데이터베이스에 저장할 데이터 타입에 대해 set, get 설정
public class Another_Notice_List_Item {

    private int idInt;
    private String titleStr;
    private String dateStr;
    private String nameStr;

    public void setId(int id) {
        idInt = id;
    }
    public void setDate(String date) {
        dateStr = date;
    }
    public void setTitle(String title) {
        titleStr = title;
    }
    public void setName(String name) {
        nameStr = name;
    }

    public int getInt() {
        return this.idInt;
    }
    public String getDate() {
        return this.dateStr;
    }
    public String getTitle() {
        return this.titleStr;
    }
    public String getName() {
        return this.nameStr;
    }

}
