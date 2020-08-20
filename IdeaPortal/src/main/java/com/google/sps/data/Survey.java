package com.google.sps.data;
import java.util.Arrays; 
import java.util.ArrayList; 

public final class Survey{
    private final long ProjectID;
    private final long authorId;
    private ArrayList<Integer> ageGroupCount; //0-14, 15-24, 25-65, >65

    public Survey(long ProjectID, long authorId){
        this.ProjectID= ProjectID;
        this.authorId = authorId;
        ageGroupCount = new ArrayList<Integer>();
        for(int i=0;i<4;i++)
            ageGroupCount.add(0);
    }

    //Will add getters and setters as per requirement
}
