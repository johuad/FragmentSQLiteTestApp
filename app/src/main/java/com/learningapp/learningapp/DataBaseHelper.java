package com.learningapp.learningapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    // Constants
    public static final String PERSON_TABLE = "PERSON_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_AGE = "AGE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "person.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + PERSON_TABLE
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_AGE + " INT)";

        // Create main table using table statement.
        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(Person person) {
        // Initialize writable database & content values.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Put content values.
        cv.put(COLUMN_NAME, person.getName());
        cv.put(COLUMN_AGE, person.getAge());

        // Insert into database.
        long insert = db.insert(PERSON_TABLE, null, cv);
        return insert != -1;
    }

    public void deleteOne(Person person) {
        // Find person in the database. If found, delete it and return true.
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PERSON_TABLE + " WHERE " + COLUMN_ID + " = " + person.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        cursor.close();
        db.close();
    }

    public ArrayList<Person> getAll() {
        // Initialize an arraylist to hold our database entries.
        ArrayList<Person> list = new ArrayList<>();
        // Choose table to select from.
        String queryString = "SELECT * FROM " + PERSON_TABLE;
        // Get a readable database.
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            // Loop through the cursor and create Person objects, put them into the list.
            do {
                int personID = cursor.getInt(0);
                String personName = cursor.getString(1);
                int personAge = cursor.getInt(2);
                // Create person object from table data.
                Person newPerson = new Person(personID, personName, personAge);
                list.add(newPerson);
            // Iterate until end of table.
            } while (cursor.moveToNext());
        }
        // Close cursor and database.
        cursor.close();
        db.close();

        return list;
    }
}
