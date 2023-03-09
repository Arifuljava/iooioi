package com.grozziie.testingApplications;

public class ModelImage {
    String image,imageurl;

    public ModelImage() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public ModelImage(String image, String imageurl) {
        this.image = image;
        this.imageurl = imageurl;
    }
}
