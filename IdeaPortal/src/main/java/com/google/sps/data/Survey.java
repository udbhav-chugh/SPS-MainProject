package com.google.sps.data;

public final class Survey{
    private final String authorId;
    private Arrays<int> ageGroupCount; //0-14, 15-24, 25-65, >65

    public Survey(String authorId){
        this.authorId = authorId;
        ageGroupCount = new int[4];
        Arrays.fill(ageGroupCount, 0);
    }

    //Will add getters and setters as per requirement
}
