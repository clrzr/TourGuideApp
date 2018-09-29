package com.clrzr.googlealc.eko;

public class Content {
    /*The name of the feature*/
    private String mAttractionName;


    /*Address of the current feature in Mombasa*/
    private String mAttractionAddress;

    /*Year of completion of construction*/
    private String mAttractionYear;

    /*Add a variable to reference the image resource ID for every feature*/
    private int mImageResourceId;


    /**
     * This constructor is for objects with a completion year
     *
     * @param attractionName           is the name of the feature
     * @param attractionAddress        is the address of the feature
     * @param attractionYear is the year the feature's construction work was completed
     */
    public Content(String attractionName, String attractionAddress, String attractionYear, int imageResourceId) {
        mAttractionName = attractionName;
        mAttractionAddress = attractionAddress;
        mImageResourceId = imageResourceId;
        mAttractionYear = attractionYear;
    }

    /**
     * Generate getters for all the variables describing a {@link Content} object
     */
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