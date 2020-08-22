package com.google.sps.data;

public final class Vote{
    private final long productID;
    private final long userId;
    private Integer voteValue; // -1-> downvote , 0 -> no vote, 1 -> upvote

    public Vote(long productID,long userId, Integer voteValue){
        this.productID= productID;
        this.userId = userId;
        this.voteValue = voteValue;
    }

    //Will add getters and setters as per requirement
}
