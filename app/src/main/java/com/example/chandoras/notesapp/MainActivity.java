package com.example.chandoras.notesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    ArrayList<Data> notesList;
    ListView listView;
    NotesAdapter adapter;

    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);
        data = new Data();


        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
        displayAllNotes();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

               data = new Data();
               data = notesList.get(position);


                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                intent.putExtra("TITLE", data.getTitle());
                intent.putExtra("INDEX", data.getId());
                intent.putExtra("DESCRIPTION", data.getDescp());

                data = notesList.get(position);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        notesList.clear();
        displayAllNotes();
    }

    private void displayAllNotes() {
        NotesDpHelper dbHelper = new NotesDpHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {NotesContract.NoteAdd._ID,
                NotesContract.NoteAdd.COLUMN_TITLE,
                NotesContract.NoteAdd.COLUMN_DESCP};

        Cursor cursor = db.query(NotesContract.NoteAdd.TABLE_NAME, projection, null, null, null, null, null);

        int idColumnIndex = cursor.getColumnIndex(NotesContract.NoteAdd._ID);
        int idTitleIndex = cursor.getColumnIndex(NotesContract.NoteAdd.COLUMN_TITLE);
        int idDescpIndex = cursor.getColumnIndex(NotesContract.NoteAdd.COLUMN_DESCP);


        while (cursor.moveToNext()) {

            int currentId   =  currentId = cursor.getInt(idColumnIndex);
            String currentTitle =    currentTitle = cursor.getString(idTitleIndex);

            String currentDescp=   currentDescp = cursor.getString(idDescpIndex);

            Data data = new Data(currentTitle, currentDescp, currentId);
            notesList.add(data);
        }

        adapter = new NotesAdapter(this, 0, notesList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
}
