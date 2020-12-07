package com.amol.waterbill.model;

/**
 * Created by amolmhatre on 12/3/20
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserListModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("connection")
    @Expose
    private String connection;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("pending_bill")
    @Expose
    private String pendingBill;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPendingBill() {
        return pendingBill;
    }

    public void setPendingBill(String pendingBill) {
        this.pendingBill = pendingBill;
    }

}
