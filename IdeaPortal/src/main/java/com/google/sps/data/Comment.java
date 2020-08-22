package com.google.sps.data;
import java.util.ArrayList; 
import java.util.List;
import java.util.Arrays;

public final class Comment{
    private final long ProjectID;
    private final long commentAuthorId;
    private final String text;
    private final String suggestion;
    private final long timestamp;
    private double sentimentAnalysisScore;

    public Comment(long ProjectID, long commentAuthorId, String text, String suggestion, long timestamp, double sentimentAnalysisScore){
        this.ProjectID = ProjectID;
        this.commentAuthorId = commentAuthorId;
        this.text = text;
        this.suggestion = suggestion;
        this.timestamp = timestamp;
        this.sentimentAnalysisScore = sentimentAnalysisScore;
    }

    //Will add getters and setters as per requirement
}