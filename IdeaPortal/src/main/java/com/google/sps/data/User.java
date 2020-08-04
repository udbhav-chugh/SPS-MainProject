package com.google.sps.data;
import java.util.Date;

public final class User{
    private final String email;
    private final String name;
    private final Date dob;
    private final String teamName;
    private final String imageUrl;

    public User(String email, String name, Date dob, String teamName, String imageUrl){
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.teamName = teamName;
        this.imageUrl = imageUrl;
    }
}