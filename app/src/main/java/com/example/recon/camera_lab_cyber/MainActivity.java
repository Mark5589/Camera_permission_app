package com.example.recon.camera_lab_cyber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextBottomCmd(View view) {
        EditText name_insertedView = (EditText)findViewById(R.id.edit_text_bar);
        String name_inserted = name_insertedView.getText().toString();


        if(name_inserted.length() >= 3){
            Intent getSecondScreenIntent = new Intent(this, SecondScreen.class);
            getSecondScreenIntent.putExtra("Name", name_inserted);
            startActivity(getSecondScreenIntent);
        }
        else{
            Toast.makeText(this, "The name is too Short (3 letters at least)", Toast.LENGTH_LONG).show();
        }
    }
}
