package com.example.ariel.savingdata;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ariel on 8/10/2016.
 */
public class SPHandler {

    Context context;
    String prefName; /* Name of the shared preference file */
    SharedPreferences sharedPref; /*SharedPreferences object used to read save data and to get the Editor object to write data */

    public SPHandler(Context context){
        this.context = context;
        sharedPref = context.getSharedPreferences(
                prefName, Context.MODE_PRIVATE);

    }

    /* Write data to sharedPreferences */
    public void writeFirstName(String firstName){
        SharedPreferences.Editor editor = sharedPref.edit(); /*Editor object used to write data */
        editor.putString("first_name",firstName); /*first_name is the key and firstName is the value */
        /*Alternatively, you can putInt, putBoolean, putLong, putFloat */
        editor.commit();/*Saves the changes. Without commit(), the data will not be saved */
    }

    public String getFirstName(){

        String firstName = sharedPref.getString("first_name","no value found..."); /*gets the value of key first_name. If the key does not exist, it will return the second argument */

        return firstName;
    }


}
