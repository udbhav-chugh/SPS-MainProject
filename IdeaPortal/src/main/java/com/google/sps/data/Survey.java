package com.google.sps.data;
import java.util.Arrays; 
import java.util.ArrayList; 

public final class Survey{
    private final long ProjectID;
    private final long authorId;
    Integer ageGroupCount; //0-14, 15-24, 25-65, >65 //Represents index of age group from 0 to 3

    public Survey(long ProjectID, long authorId, Integer ageGroupCount){
        this.ProjectID= ProjectID;
        this.authorId = authorId;
        this.ageGroupCount = ageGroupCount;
    }

    //Will add getters and setters as per requirement
}
