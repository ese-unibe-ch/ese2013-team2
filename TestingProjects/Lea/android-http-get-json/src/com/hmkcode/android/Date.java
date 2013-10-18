package com.hmkcode.android;

public class Date {
	long startTimeSec;
    long endTimeSec;
    String day;
    
    public Date(long startT, long endT, String d){
            startTimeSec = startT;
            endTimeSec = endT;
            day = d;
    }
    
    /**
     *  display date in format: 'mo 20:15'
     */
    public String toString(){
            return day;
            
    }

}
