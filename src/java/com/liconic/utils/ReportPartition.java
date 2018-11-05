package com.liconic.utils;

public class ReportPartition {
    
    private String Parttiton;
    private String localPathImport;
    private String localPathExport;
    private String remotePathImport;
    private String remotePathExport;

    public ReportPartition(String Parttiton, String localPathImport, String localPathExport, String remotePathImport, String remotePathExport) {
        this.Parttiton = Parttiton;
        this.localPathImport = localPathImport;
        this.localPathExport = localPathExport;
        this.remotePathImport = remotePathImport;
        this.remotePathExport = remotePathExport;
    }

    public String getParttiton() {
        return Parttiton;
    }

    public void setParttiton(String Parttiton) {
        this.Parttiton = Parttiton;
    }

    public String getLocalPathImport() {
        return localPathImport;
    }

    public String getLocalPathExport() {
        return localPathExport;
    }

    public String getRemotePathImport() {
        return remotePathImport;
    }

    public String getRemotePathExport() {
        return remotePathExport;
    }

    public void setLocalPathImport(String localPathImport) {
        this.localPathImport = localPathImport;
    }

    public void setLocalPathExport(String localPathExport) {
        this.localPathExport = localPathExport;
    }

    public void setRemotePathImport(String remotePathImport) {
        this.remotePathImport = remotePathImport;
    }

    public void setRemotePathExport(String remotePathExport) {
        this.remotePathExport = remotePathExport;
    }


}
