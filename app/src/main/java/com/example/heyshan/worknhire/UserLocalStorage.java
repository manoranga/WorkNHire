package com.example.heyshan.worknhire;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStorage {
    public  final static String spName = "userDetails";
    SharedPreferences userLocalDatabase;

    public void setUserDetails(UserModel userModel){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("_id", userModel.get_id());
        spEditor.putString("fname", userModel.getFname());
        spEditor.putString("lname", userModel.getLname());
        spEditor.putString("mobileno", userModel.getMobileno());
        spEditor.putString("password", userModel.getPassword());
        spEditor.putString("email", userModel.getEmail());

        spEditor.commit();
    }
    public UserModel getUserDetails(){
        String _id = userLocalDatabase.getString("_id","");
        String fname = userLocalDatabase.getString("fname","");
        String lname = userLocalDatabase.getString("lname","");
        String mobileno = userLocalDatabase.getString("mobileno","");
        String password = userLocalDatabase.getString("password","");
        String email = userLocalDatabase.getString("email","");

        UserModel userModel = new UserModel(_id,fname,lname,password,mobileno,email);
        return  userModel;

    }


    public UserLocalStorage(Context context) {
        userLocalDatabase = context.getSharedPreferences(spName,0);
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn", false))
            return true;
        else
            return false;
    }

    public void clearUser(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();

    }
}
