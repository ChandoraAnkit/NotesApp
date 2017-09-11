package com.example.chandoras.notesapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class AddNoteActivity extends AppCompatActivity {
    EditText mEditTextTitle, mEditTextDesc;
    NotesDpHelper notesDbHelper;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mEditTextTitle = (EditText) findViewById(R.id.title);
        mEditTextDesc = (EditText) findViewById(R.id.descp);

        Intent intent = getIntent();
        id  = intent.getIntExtra("INDEX",-1);
        String title = intent.getStringExtra("TITLE");
        String decp = intent.getStringExtra("DESCRIPTION");

        mEditTextTitle.setText(title);
        mEditTextDesc.setText(decp);

         notesDbHelper = new NotesDpHelper(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            insertNote();
            finish();
            return true;
        } else if (item.getItemId() == R.id.delete){
           deleteNote(id);
            finish();
            return true;

        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void deleteNote(int id) {
        SQLiteDatabase db = notesDbHelper.getWritableDatabase();
        String SQL_UPDATE_TABLE = " DELETE FROM "+ NotesContract.NoteAdd.TABLE_NAME + " WHERE "+ NotesContract.NoteAdd._ID + " = "+id +" ;";
        db.execSQL(SQL_UPDATE_TABLE);
    }

    private void insertNote() {
        String title = mEditTextTitle.getText().toString().trim();
        String description = mEditTextDesc.getText().toString().trim();


        SQLiteDatabase db =  notesDbHelper.getWritableDatabase();

        ContentValues values =  new ContentValues();
        values.put(NotesContract.NoteAdd.COLUMN_TITLE,title);
        values.put(NotesContract.NoteAdd.COLUMN_DESCP,description);

        long newRowId = db.insert(NotesContract.NoteAdd.TABLE_NAME,null,values);




    }
}
