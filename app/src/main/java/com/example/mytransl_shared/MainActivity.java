package com.example.mytransl_shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText inT, rusT, find_words;
    TextView text, mode;
    Button swap, delete, add, find;
    int swapCount = 0;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rusT = findViewById(R.id.edit_word1);
        inT = findViewById(R.id.edit_word2);
        find_words=findViewById(R.id.edit_find);
        text = findViewById(R.id.dictionary);
        mode = findViewById(R.id.mode);
        add = findViewById(R.id.add_word);
        find = findViewById(R.id.show_translate);
        delete = findViewById(R.id.clear);
        swap = findViewById(R.id.swap);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        add.setOnClickListener(view -> {
            editor = sharedPreferences.edit();
            editor.putString( rusT.getText().toString(), inT.getText().toString());
            editor.apply();
        });

        find.setOnClickListener(view -> {
           if (swapCount % 2 == 0){
               String savedText = sharedPreferences.getString(find_words.getText().toString(), "");
               text.setText(find_words.getText().toString() + " - " + savedText + "; ");}
            else
            {
                Map<String,?> keys = sharedPreferences.getAll();
                for(Map.Entry<String,?> entry : keys.entrySet()){
                    if (entry.getValue().toString().equals(find_words.getText().toString()))
                    {text.setText(entry.getKey() + " - " + entry.getValue().toString());}
                    else text.setText("Не найдено");
                }
            }
        });
        delete.setOnClickListener(view -> {
            rusT.setText("");
            inT.setText("");
        });

        swap.setOnClickListener(view -> {
            EditText temp = inT;
            inT = rusT;
            rusT = temp;
            if (swapCount % 2 == 1)
             mode.setText("C русского языка на иностранный");
            else
              mode.setText("C иностранного языка на русский");
            swapCount++;
            Log.i("!", Integer.toString(swapCount));
        });


    }
}