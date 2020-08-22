package com.google.sps.data;
import java.util.ArrayList; 
import java.util.List;
import java.util.Arrays;

public final class Comment{
    private final long ProjectID;
    private final long commentAuthorId;
    private final String text;
    private final String suggestion;
    private final List<String> suggestionKeywords; //tf-idf to extract most frequent words from suggestions to make a summary of suggestions
    private final long timestamp;
    private double sentimentAnalysisScore;

    public Comment(long ProjectID, long commentAuthorId, String text, String suggestion, List<String> suggestionKeywords, long timestamp, double sentimentAnalysisScore){
        this.ProjectID = ProjectID;
        this.commentAuthorId = commentAuthorId;
        this.text = text;
        this.suggestion = suggestion;
        this.suggestionKeywords = suggestionKeywords;
        // this.suggestionKeywords = suggestionKeywords;
        this.timestamp = timestamp;
        this.sentimentAnalysisScore = sentimentAnalysisScore;
    }

    //Will add getters and setters as per requirement
}