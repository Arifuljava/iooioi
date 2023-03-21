package com.grozziie.testingApplications;

public class textModel {
    String text,id,roomid;

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public String getRoomid() {
        return roomid;
    }

    public textModel(String text, String id, String roomid) {
        this.text = text;
        this.id = id;
        this.roomid = roomid;
    }

    public textModel() {
    }
}
