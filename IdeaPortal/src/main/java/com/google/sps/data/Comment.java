package com.google.sps.data;

public final class Comment{
    private final String userId;
    private final String text;
    private final String suggestion;
    private final ArrayList<String> competitors;
    private final long timestamp;
    private Arrays<int> ageGroupCount; //0-14, 15-24, 25-65, >65
    private double sentimentAnalysisScore;

    public Comment(String userId, String text, String suggestion, ArrayList<String> competitors, long timestamp){
        this.userId = userId;
        this.text = text;
        this.suggestion = suggestion;
        this.competitors = competitors;
        this.timestamp = timestamp;
        ageGroupCount = new int[4];
        Arrays.fill(ageGroupCount, 0);
        sentimentAnalysisScore = 0;
    }
}