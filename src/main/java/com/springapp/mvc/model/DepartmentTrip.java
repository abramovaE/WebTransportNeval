package com.springapp.mvc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by oem on 21.05.19.
 */
public class DepartmentTrip {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("worker")
    @Expose
    private long worker;

    @SerializedName("f")
    @Expose
    private String f;

    @SerializedName("i")
    @Expose
    private String i;

    @SerializedName("o")
    @Expose
    private String o;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("startTime")
    @Expose
    private String startTime;

    @SerializedName("stopTime")
    @Expose
    private String stopTime;

    @SerializedName("comm")
    @Expose
    private String comm;

    @SerializedName("addr")
    @Expose
    private String addr;

    @SerializedName("addr_id")
    @Expose
    private long addrId;

    @SerializedName("department")
    @Expose
    private String department;


    public DepartmentTrip() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorker() {
        return worker;
    }

    public void setWorker(long worker) {
        this.worker = worker;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public long getAddrId() {
        return addrId;
    }

    public void setAddrId(long addrId) {
        this.addrId = addrId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return  "f='" + f + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                ", comm='" + comm + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    public String getIdColorForAddr(String pointDepAddr, String pointArrAddr){


        if(addr != null && !addr.isEmpty()){


            String addr = this.addr.trim();
            pointArrAddr = pointArrAddr.replaceAll("\\s+", " ").trim();
            pointDepAddr = pointDepAddr.replaceAll("\\s+", " ").trim();

            if(addr.equals(pointArrAddr) || addr.equals(pointDepAddr)){
                return "";
            }
            else {
                System.out.println(addr.equals(pointArrAddr));
                System.out.println(addr.equals(pointDepAddr));
                return "diffAddr";
            }
        }


        return "";

    }


}
