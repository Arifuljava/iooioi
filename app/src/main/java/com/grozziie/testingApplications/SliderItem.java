package com.grozziie.testingApplications;

public class SliderItem {
    String  image;
    String text,data;

    public SliderItem() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public SliderItem(String image, String text, String data) {
        this.image = image;
        this.text = text;
        this.data = data;
    }
}
