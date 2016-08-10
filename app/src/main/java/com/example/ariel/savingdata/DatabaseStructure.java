package com.example.ariel.savingdata;

/**
 * Created by Ariel on 8/7/2016.
 */
public final class DatabaseStructure {

    public DatabaseStructure(){

    }

    /*First Table name */
    public static final String TABLE_NAME = "your_table_name";
    /*First Table columns */
    public static final String FIRST_COLUNMN = "your_first_column";
    public static final String SECOND_COLUNMN = "your_second_column";
    public static final String THIRD_COLUNMN = "your_third_column";
    public static final String FOURTH_COLUNMN = "your_fourth_column";

    /*Second Table name */
    public static final String TABLE_NAME2 = "your_second_table_name";
    /*Second Table columns */
    public static final String FIRST_COLUNMN2 = "your_first_column";
    public static final String SECOND_COLUNMN2 = "your_second_column";
    public static final String THIRD_COLUNMN2 = "your_third_column";


    /*SQL queries to create the tables */
    public static final String SQL_CREATE_FIRST_TABLE =
          "CREATE TABLE " + TABLE_NAME + " (" +
                  FIRST_COLUNMN + " INTEGER PRIMARY KEY," +
                  SECOND_COLUNMN + " TEXT, "+
                  THIRD_COLUNMN + " TEXT, "+
                  FOURTH_COLUNMN+ " TEXT)";

    public static final String SQL_CREATE_SECOND_TABLE =
            "CREATE TABLE " + TABLE_NAME2 + " (" +
                    FIRST_COLUNMN2 + " TEXT," +
                    SECOND_COLUNMN2 + " TEXT, "+
                    THIRD_COLUNMN2 + " TEXT)";

    /*SQL queries to delete the tables for onUpgrade() */
    public static final String SQL_DELETE_FIRST_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String SQL_DELETE_SECOND_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME2;
}
