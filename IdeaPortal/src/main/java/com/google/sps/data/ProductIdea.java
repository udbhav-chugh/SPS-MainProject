package com.google.sps.data;

import java.util.ArrayList; 
import java.util.Arrays; 

public final class ProductIdea{
    enum category {
        Agriculture,
        Sustainbility,
        Privacy,
        Ed-Tech,
        Travel and Tourism,
        Other  
    }

    private final String title;
    private final String authorID;
    private final long timestamp;
    private final ArrayList<category> categories;
    private final String imageUrl;
    private final String videoUrl;
    private final String description;

    //To be calculated during loading of stats page
    // private double finalRating;
    // private Arrays<int> sentimentScoreCount; //0-20, 20-40, 40-60, 60-80, 80-100
    // private int upvotes;
    // private int downvotes;


    public ProductIdea(String title, String authorID, long timestamp, ArrayList<category> categories, String imageUrl, String videoUrl, String description=""){
        this.title = title;
        this.authorID = authorID;
        this.timestamp = timestamp;
        this.categories = categories;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.description = description;
    }

    //Will add getters and setters as per requirement

}