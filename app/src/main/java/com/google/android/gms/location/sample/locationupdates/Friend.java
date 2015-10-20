package com.google.android.gms.location.sample.locationupdates;

import android.provider.BaseColumns;

/**
 * Created by Arvind on 19-10-2015.
 */
public class Friend {

    //Database
    public static final String DATABASE_NAME = "stamford_Track.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "friend";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String LOC_NAME = "Loc_name";
        public static final String LATITUDE = "Latitude";
        public static final String LONGITUDE = "Longitude";
        public static final String TIME = "Time";


    }

    private int id;
    private String locationName;
    private String latitude ;
    private String longitude;
    private String time;

    //Default Constructor
    public Friend() {

    }
    //Constructor
    public Friend(int id, String locationName, String latitude, String longitude, String time) {
        this.id = id;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;

    }
    //Getter, Setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitute(String longitude) {
        this.longitude = longitude;
    }

    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }



}
