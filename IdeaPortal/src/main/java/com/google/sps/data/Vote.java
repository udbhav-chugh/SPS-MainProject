package com.google.sps.data;

public final class Vote{
    private final long userId;
    private int voteValue; // -1-> downvote , 0 -> no vote, 1 -> upvote

    public Vote(long userId, int voteValue){
        this.userId = userId;
        this.voteValue = voteValue;
    }

    //Will add getters and setters as per requirement
}