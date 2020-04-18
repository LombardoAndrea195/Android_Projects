package com.example.andrea.note;

import android.content.ContentValues;




import android.app.Notification;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

    public class EditorActivity extends AppCompatActivity {

        private String noteFilter;
        private String action;
        private EditText editor;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_editor);

            editor = (EditText) findViewById(R.id.editText);

            Intent intent = getIntent();

            Uri uri = intent.getParcelableExtra(NotesProvider.CONTENT_ITEM_TYPE);

            if (uri == null) {

                action = Intent.ACTION_INSERT;
                setTitle(getString(R.string.new_note));
            }

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            if (action.equals(Intent.ACTION_EDIT)) {

                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            switch (id) {
                case android.R.id.home:
                    finishEditing();
                    break;
                case R.id.action_delete:
                    deleteNote();
                    break;
            }

            return true;
        }

        private void deleteNote() {
            getContentResolver().delete(NotesProvider.CONTENT_URI ,
            noteFilter,null);
            Toast.makeText(this, getString(R.string.note_deleted),
                    Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }


        private  void finishEditing(){
            String newText = editor.getText().toString().trim();

            switch (action) {
                case Intent.ACTION_INSERT:
                    if(newText.length()== 0)
                    {
                        setResult(RESULT_CANCELED);

                    }

                    else {

                        insertNote(newText);

                    }

            }
            finish();

        }

        private void insertNote(String noteText) {
            ContentValues values = new ContentValues();
            values.put(DBOpenHelper.NOTE_TEXT, noteText);
            getContentResolver().insert(NotesProvider.CONTENT_URI, values);
            setResult(RESULT_OK);

        }
        public void onBackPressed() {
            finishEditing();
        }
    }

