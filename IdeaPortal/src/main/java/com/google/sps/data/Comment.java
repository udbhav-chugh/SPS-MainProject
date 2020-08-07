package com.google.sps.data;

public final class Comment{
    private final String commentAuthorId;
    private final String text;
    private final String suggestion;
    private final ArrayList<String> suggestionKeywords; //tf-idf to extract most frequent words from suggestions to make a summary of suggestions
    private final long timestamp;
    private double sentimentAnalysisScore;

    public Comment(String commentAuthorId, String text, String suggestion, ArrayList<String> suggestionKeywords, long timestamp){
        this.commentAuthorId = commentAuthorId;
        this.text = text;
        this.suggestion = suggestion;
        this.suggestionKeywords = suggestionKeywords;
        this.timestamp = timestamp;
        sentimentAnalysisScore = 0;
    }

    //Will add getters and setters as per requirement
}