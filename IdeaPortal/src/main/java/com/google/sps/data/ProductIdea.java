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

    private final String name;
    private final String userID;
    private final long timestamp;
    private final ArrayList<category> categories;
    private final String imageUrl;
    private final String videoUrl;
    private final String description;
    private double finalRating;
    private Arrays<int> sentimentScoreCount; //0-20, 20-40, 40-60, 60-80, 80-100
    private int upvotes;
    private int downvotes;


    public ProductIdea(String name, String userID, long timestamp, ArrayList<category> categories, String imageUrl, String videoUrl, String description=""){
        this.name = name;
        this.userID = userID;
        this.timestamp = timestamp;
        this.categories = categories;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.description = description;
        this.finalRating = 0;
        sentimentScoreCount = new int[5];
        Arrays.fill(sentimentScoreCount, 0);
        this.upvotes = 0;
        this.downvotes = 0;
    }
}