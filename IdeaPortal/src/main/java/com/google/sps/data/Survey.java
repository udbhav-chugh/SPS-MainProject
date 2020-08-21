package com.google.sps.data;
import java.util.Arrays; 
import java.util.ArrayList; 

public final class Survey{
    private final long productID;
    private final long authorId;
    private int ageGroupCount; //0-14, 15-24, 25-65, >65

    public Survey(long productID, long authorId){
        this.productID= productID;
        this.authorId = authorId;
        this.ageGroupCount = -1;
        
    }

    //Will add getters and setters as per requirement
}
