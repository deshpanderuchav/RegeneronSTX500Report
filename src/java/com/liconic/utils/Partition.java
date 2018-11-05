package com.liconic.utils;

public class Partition {
    
    private int id;
    private String partitionId;
    private boolean isAlarm;

    public Partition(int id, String partitionId) {
        this.id = id;
        this.partitionId = partitionId;
        
        isAlarm = false;
    }

    public int getId() {
        return id;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public boolean isIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(boolean isAlarm) {
        this.isAlarm = isAlarm;
    }
       
    
}
