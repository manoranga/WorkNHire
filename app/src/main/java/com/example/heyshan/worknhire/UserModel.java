package com.example.heyshan.worknhire;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {
    @SerializedName("_id")
    private String _id;
    @SerializedName("lname")
    private String lname;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("fname")
    private String fname;
    @SerializedName("mobileno")
    private String mobileno;

    public UserModel(String _id, String lname, String password, String email, String fname, String mobileno) {
        this._id = _id;
        this.lname = lname;
        this.password = password;
        this.email = email;
        this.fname = fname;
        this.mobileno = mobileno;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
}
