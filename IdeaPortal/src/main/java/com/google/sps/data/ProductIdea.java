package com.google.sps.data;

import java.util.ArrayList; 
import java.util.Arrays; 

public final class ProductIdea{
    enum category {
        Sustainbility,
        Privacy,
        Agriculture,
        Tourism,
        Other,  
    }

    private final String title;
    private final long authorID;
    private final long timestamp;
    private final category categories;
    private final String imageUrl;
    private final String videoUrl;
    private final String description;

    //To be calculated during loading of stats page
    // private double finalRating;
    // private Arrays<int> sentimentScoreCount; //0-20, 20-40, 40-60, 60-80, 80-100
    // private int upvotes;
    // private int downvotes;


    public ProductIdea(String title, long authorID, long timestamp, category categories, String imageUrl, String videoUrl, String description){
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