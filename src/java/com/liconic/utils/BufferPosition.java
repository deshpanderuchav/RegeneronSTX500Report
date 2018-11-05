package com.liconic.utils;

public class BufferPosition {
 
    private String DeviceID;
    private String CassetteId;
    private String LivelId;
    private String Barcode;

    
    public BufferPosition(String DeviceID, String CassetteId, String LivelId, String Barcode) {
        this.DeviceID = DeviceID;
        this.CassetteId = CassetteId;
        this.LivelId = LivelId;
        this.Barcode = Barcode;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public String getCassetteId() {
        return CassetteId;
    }

    public String getLivelId() {
        return LivelId;
    }

    public String getBarcode() {
        return Barcode;
    }
    
    
    
}
