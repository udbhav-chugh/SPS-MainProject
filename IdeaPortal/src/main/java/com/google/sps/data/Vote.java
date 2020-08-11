package com.google.sps.data;

public final class Vote{
    private final String userId;
    private int voteValue; // -1-> downvote , 0 -> no vote, 1 -> upvote

    public Vote(String userId, int voteValue){
        this.userId = userId;
        this.voteValue = voteValue;
    }

    //Will add getters and setters as per requirement
}