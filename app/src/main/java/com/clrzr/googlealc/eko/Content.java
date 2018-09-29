package com.clrzr.googlealc.eko;

public class Content {
    private String mAttractionName;
    private String mAttractionAddress;
    private String mAttractionYear;
    private int mImageResourceId;

    public Content(String attractionName, String attractionAddress, String attractionYear, int imageResourceId) {
        mAttractionName = attractionName;
        mAttractionAddress = attractionAddress;
        mImageResourceId = imageResourceId;
        mAttractionYear = attractionYear;
    }

    public String getAttractionName() {
        return mAttractionName;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public String getAttractionAddress() {
        return mAttractionAddress;
    }

    public String getAttractionYear() {
        return mAttractionYear;
    }
}