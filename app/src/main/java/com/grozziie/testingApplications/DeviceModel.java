package com.grozziie.testingApplications;

public class DeviceModel {
    String devicename,deviceaddress,uuid,mydevicetime,mydevicename,connectddate;

    public DeviceModel() {
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDeviceaddress() {
        return deviceaddress;
    }

    public void setDeviceaddress(String deviceaddress) {
        this.deviceaddress = deviceaddress;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMydevicetime() {
        return mydevicetime;
    }

    public void setMydevicetime(String mydevicetime) {
        this.mydevicetime = mydevicetime;
    }

    public String getMydevicename() {
        return mydevicename;
    }

    public void setMydevicename(String mydevicename) {
        this.mydevicename = mydevicename;
    }

    public String getConnectddate() {
        return connectddate;
    }

    public void setConnectddate(String connectddate) {
        this.connectddate = connectddate;
    }

    public DeviceModel(String devicename,
                       String deviceaddress, String uuid,
                       String mydevicetime, String mydevicename, String connectddate) {
        this.devicename = devicename;
        this.deviceaddress = deviceaddress;
        this.uuid = uuid;
        this.mydevicetime = mydevicetime;
        this.mydevicename = mydevicename;
        this.connectddate = connectddate;
    }
}
