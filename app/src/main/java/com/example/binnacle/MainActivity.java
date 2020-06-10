package com.example.binnacle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText etBinnacle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBinnacle = findViewById(R.id.et_binnacle);
        String[] files = fileList();

        if(fileExist(files, "binnacle.txt")){
            try {
                InputStreamReader file = new InputStreamReader(openFileInput("binnacle.txt"));
                BufferedReader bufferedReader = new BufferedReader(file);
                String label = bufferedReader.readLine();
                String binnacle = "";

                while (label != null){
                    binnacle += label + "\n";
                    label = bufferedReader.readLine();
                }

                bufferedReader.close();
                file.close();

                etBinnacle.setText(binnacle);
            } catch (IOException e) {
                Toast.makeText(this, "Couldn't read binnacle", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean fileExist(String[] files, String name){
        for (int i = 0; i < files.length; i++)
            if(name.equals(files[i]))
                return true;
        return false;
    }

    public void save(View view){
        try {
            OutputStreamWriter file =  new OutputStreamWriter(openFileOutput("binnacle.txt", Activity.MODE_PRIVATE));

            file.write(etBinnacle.getText().toString());
            file.flush();
            file.close();

            Toast.makeText(this, "Binnacle is saved", Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException e) {
            Toast.makeText(this, "Couldn't save binnacle", Toast.LENGTH_SHORT).show();
        }

    }
}