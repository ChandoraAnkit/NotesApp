package com.example.chandoras.notesapp;

import android.provider.BaseColumns;

/**
 * Created by chandoras on 9/10/17.
 */

public class NotesContract  {
    private NotesContract(){}

public  static final class NoteAdd implements BaseColumns{

    public final static String TABLE_NAME = "notes";

    public final static String _ID = BaseColumns._ID;

    public final static String COLUMN_TITLE = "title";

    public final static String COLUMN_DESCP = "descp";



  }
}
