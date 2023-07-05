package com.example.cardiac_data_recoder;


public class MedicalReportModel {
    String Date;
    String Time;
    String Systolic;
    String Diastolic;
    String HeartRate;
    String Comment;
    public MedicalReportModel(){}

    public MedicalReportModel( String date, String time, String systolic,String diastolic,String heartRate,String comment) {
        Date = date;
        Time = time;
        Systolic = systolic;
        Diastolic = diastolic;
        HeartRate = heartRate;
        Comment = comment;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSystolic() {
        return Systolic;
    }

    public void setSystolic(String systolic) {
        Systolic = systolic;
    }

    public String getDiastolic() {
        return Diastolic;
    }

    public void setDiastolic(String diastolic) {
        Diastolic = diastolic;
    }

    public String getHeartRate() {
        return HeartRate;
    }

    public void setHeartRate(String heartRate) {
        HeartRate = heartRate;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
