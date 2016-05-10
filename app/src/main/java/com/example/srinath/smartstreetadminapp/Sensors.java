package com.example.srinath.smartstreetadminapp;

/**
 * Created by Karthik on 04-30-2016.
 */
public class Sensors {
    private String Sensordate;
    private String SensorManufacturer;
    private String SensorModel;
    private String SensorName;
    private String LastUpdated;
    private String SensorStatus;
private String SensorID;

Sensors(String date,String manu,String model,String name,String id,String lastupdated,String status){
this.Sensordate=date;
    this.SensorManufacturer=manu;
    this.SensorModel=model;
    this.SensorName=name;
    this.SensorID = id;
    this.LastUpdated=lastupdated;
    this.SensorStatus=status;
}
Sensors(){}
public String getSensordate(){
    return Sensordate;
}
public String getSensorManufacturer(){
    return SensorManufacturer;

}
public String getSensorModel(){
    return SensorModel;
}
    public String getSensorName()
{
    return SensorName;
}
    public String getSensorStatus(){
        return SensorStatus;
    }
    public String getLastUpdated(){
        return LastUpdated;
    }
public String getSensorID(){
    return SensorID;
}
    @Override
    public String toString(){
        return "Sensor Name is"+getSensorName();

    }

}
