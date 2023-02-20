package com.example.nimgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private Button kezd;
    private Button info;
    private SeekBar diffselect;
    private SeekBar numselect;
    public int difficulty = 0;
    public int numbers = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kezd = (Button) findViewById(R.id.start);
        info = (Button) findViewById(R.id.info);
        diffselect = (SeekBar) findViewById(R.id.diff);
        numselect = (SeekBar) findViewById(R.id.numsel);

        kezd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = diffselect.getProgress();
                numbers = numselect.getProgress()+3;

                openMainActivity2();
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity3();
            }
        });
    }

    public void openMainActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("x", difficulty);
        intent.putExtra("y", numbers);
        //setResult(RESULT_OK, intent);
        startActivity(intent);
    }
    public void openMainActivity3(){
        Intent intent = new Intent(this, MainActivity3.class);

        startActivity(intent);
    }
}