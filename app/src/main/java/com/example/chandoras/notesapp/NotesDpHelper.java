package com.example.chandoras.notesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chandoras on 9/10/17.
 */

public class NotesDpHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "notesCollection.db";
    private final static int DATABASE_VER = 1;

    public NotesDpHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE_CREATE = " CREATE TABLE "+ NotesContract.NoteAdd.TABLE_NAME +" ( "
                 + NotesContract.NoteAdd._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  "
                + NotesContract.NoteAdd.COLUMN_TITLE +" TEXT NOT NULL, "
                + NotesContract.NoteAdd.COLUMN_DESCP + " TEXT );";
        db.execSQL(SQL_TABLE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        String SQL_DELETE_TABLE = "DROP TABLE IF EXIST "+ NotesContract.NoteAdd.TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
