package storage.practise.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class EditNoteActivity extends AppCompatActivity {
    int position;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        editText=(EditText)findViewById(R.id.editText);
        Intent intent=getIntent();
        position=intent.getIntExtra("position",-1);
        if(position!=-1)
        {
            editText.setText(MainActivity.Notes.get(position));
        }
        else{
            MainActivity.Notes.add("");
            position=MainActivity.Notes.size()-1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.Notes.set(position, String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("storage.practise.notes", Context.MODE_PRIVATE);
                HashSet<String> set =new HashSet<>(MainActivity.Notes);
                sharedPreferences.edit().putStringSet("Notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
