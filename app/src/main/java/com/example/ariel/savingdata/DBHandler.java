package com.example.ariel.savingdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by Ariel on 8/7/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1; /*by incrementing the database version you will call onUpgrade */
    public static final String DATABASE_NAME = "mydb.db";
    DatabaseStructure structure = new DatabaseStructure(); /*the class that holds the structure of our database. table, column names and create delete queries */

    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /* creating the two tables with the queries we defined in DatabaseStructure */
        db.execSQL(structure.SQL_CREATE_FIRST_TABLE);
        db.execSQL(structure.SQL_CREATE_SECOND_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(structure.SQL_DELETE_FIRST_TABLE);
        db.execSQL(structure.SQL_DELETE_SECOND_TABLE);

        /*after deleting both tables running oncreate to reconstruct two new empty ones */
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }


    /*add data to the database. create a method that accepts arguments of the data you need to insert  */
    public void addData(String data1, String data2, String data3){

        /*object that lets you write to your database */
        SQLiteDatabase db = getWritableDatabase();

        /*create content values where column name is the key and data is the value*/
        ContentValues values = new ContentValues();
        values.put(structure.SECOND_COLUNMN,data1);
        values.put(structure.THIRD_COLUNMN,data2);
        values.put(structure.FOURTH_COLUNMN,data3);

        /*insert data into database */
        db.insert(structure.TABLE_NAME,null,values);
    }

    /*retrieve data from database */
    public ArrayList readData(String argument){

        /*object that lets you read from the database */
        SQLiteDatabase db = getReadableDatabase();

        /*ArrayList that will hold the data we retrieve */
        ArrayList<String> returnData = new ArrayList<>();

        /*define which columns from the database you would like to retrieve */
        String[] columns = {
                structure.FIRST_COLUNMN,
                structure.SECOND_COLUNMN,
                structure.THIRD_COLUNMN
            };

        /*columns for the where clause. ? will be replaced by selectionArgs */
        String selection = structure.SECOND_COLUNMN + " =?";

        /*values for the where clause */
        String[] selectionArgs = {argument};
        /*selection and selectionArgs are equivalent to "WHERE FIRST_COLUMN = argument"

        /*define how you want your columns to be sorted */
        String sortBy = structure.FIRST_COLUNMN + " DESC";

        Cursor c = db.query(structure.TABLE_NAME,columns,selection,selectionArgs,null,null,sortBy);

        /*defining column index for each row */
        int firstRow = c.getColumnIndexOrThrow(structure.FIRST_COLUNMN);
        int secondRow = c.getColumnIndexOrThrow(structure.SECOND_COLUNMN);
        int thirdColumn = c.getColumnIndexOrThrow(structure.THIRD_COLUNMN);

        /*loop through the table to read every row that is relevant to our query */
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            /*adding retrieved data to the ArrayList */
            returnData.add(c.getString(firstRow)+ ", " + c.getString(secondRow) + ", " + c.getString(thirdColumn));
        }

        return returnData;
    }

    /*delete data from database */
    public void deleteData(String argument){

        SQLiteDatabase db = getWritableDatabase();

        /*columns for the where clause */
        String selection = structure.SECOND_COLUNMN + " = ?";

        /*values for the where clause */
        String[] selectionArgs = {argument};

        db.delete(structure.TABLE_NAME,selection,selectionArgs);

    }

    public void updateData(String oldData, String newData){

        SQLiteDatabase db = getWritableDatabase();

        /*new value for specific column */
        ContentValues values = new ContentValues();
        values.put(structure.SECOND_COLUNMN,newData);

        String selection = structure.SECOND_COLUNMN + " = ?";
        String[] selectionArgs = {oldData};

        db.update(structure.TABLE_NAME,values,selection,selectionArgs);


    }
}
