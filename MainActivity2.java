package com.example.nimgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    private TextView message1;
    private TextView message2;
    private TextView win;
    private TextView lose;
    private FloatingActionButton incr;
    private FloatingActionButton decr;
    private TextView[] dispNums = new TextView[5];
    private Button btn;
    private int difficulty = 0;
    private int numbers = 0;
    private int[] theNumbers = new int[5];
    private int selected, toSubstract = 0;
    private boolean myTurn = true;
    private String d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        message1 = findViewById(R.id.msg1);
        message2 = findViewById(R.id.msg2);
        win = findViewById(R.id.playerwin);
        lose = findViewById(R.id.compwin);
        dispNums[0] = findViewById(R.id.n1);
        dispNums[1] = findViewById(R.id.n2);
        dispNums[2] = findViewById(R.id.n3);
        dispNums[3] = findViewById(R.id.n4);
        dispNums[4] = findViewById(R.id.n5);
        btn = findViewById(R.id.btn1);
        incr = findViewById(R.id.increment);
        decr = findViewById((R.id.decrement));
        Intent intent = getIntent();

        difficulty = intent.getIntExtra("x", 0);
        numbers = intent.getIntExtra("y", 3);

        switch(difficulty){
            case 0: d = "Könnyű"; break;
            case 1: d = "Közepes"; break;
            case 2: d = "Nehéz"; break;
        }
        message1.setText("Nehézség: " + d);
        message2.setText("Számok: " + Integer.toString(numbers));

        win.setVisibility(View.GONE);
        lose.setVisibility(View.GONE);

        show(false);
        genRand(4, 9, numbers);
        updateDisp();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toSubstract > 0 && myTurn && !isAllZero()){
                if(theNumbers[selected] - toSubstract >= 0 && toSubstract >= 0){
                    theNumbers[selected] -= toSubstract;
                }
                updateDisp();
                show(false);
                toSubstract = 0;
                myTurn = false;
                //-------------
                computersMove();
                //--------------
                }
            }
        });
    }

    protected void genRand(int min, int max, int n){
        Random random = new Random();
        for(int i = 0; i < 5; i++){
            if(i < n) {
                theNumbers[i] = random.nextInt(max - min) + min;
                dispNums[i].setVisibility(View.VISIBLE);
            }
            else{
                theNumbers[i] = 0;
                dispNums[i].setVisibility(View.GONE);
            }
        }
    }
    protected void updateDisp(){
        for(int i = 0; i < 5; i++){
            dispNums[i].setText(Integer.toString(theNumbers[i]));
        }
    }
    public void show(boolean b){
        if(b){btn.setVisibility((View.VISIBLE)); incr.setVisibility(View.VISIBLE); decr.setVisibility(View.VISIBLE);}
        else{btn.setVisibility((View.GONE)); incr.setVisibility(View.GONE); decr.setVisibility(View.GONE);}
    }
    public void onClck(View v)
    {
        if(myTurn && !isAllZero()) {
            String idString = v.getResources().getResourceEntryName(v.getId());

            switch (idString) {
                case "n1":
                    show(true);
                    btn.setY(dispNums[0].getY());
                    incr.setY(dispNums[0].getY() - 20);
                    decr.setY(dispNums[0].getY() - 20);
                    selected = 0;
                    break;
                case "n2":
                    show(true);
                    btn.setY(dispNums[1].getY());
                    incr.setY(dispNums[1].getY() - 20);
                    decr.setY(dispNums[1].getY() - 20);
                    selected = 1;
                    break;
                case "n3":
                    show(true);
                    btn.setY(dispNums[2].getY());
                    incr.setY(dispNums[2].getY() - 20);
                    decr.setY(dispNums[2].getY() - 20);
                    selected = 2;
                    break;
                case "n4":
                    show(true);
                    btn.setY(dispNums[3].getY());
                    incr.setY(dispNums[3].getY() - 20);
                    decr.setY(dispNums[3].getY() - 20);
                    selected = 3;
                    break;
                case "n5":
                    show(true);
                    btn.setY(dispNums[4].getY());
                    incr.setY(dispNums[4].getY() - 20);
                    decr.setY(dispNums[4].getY() - 20);
                    selected = 4;
                    break;
            }
        }
    }
    public void clckOnBlank(View v){
        if(myTurn && !isAllZero()) {
            updateDisp();
            show(false);
            toSubstract = 0;
        }
    }
    public void incrClck(View v){
        if(toSubstract > 0 && theNumbers[selected] - toSubstract >= 0 && myTurn && !isAllZero()){toSubstract--;
        dispNums[selected].setText(Integer.toString((theNumbers[selected]-toSubstract)));
        }
    }
    public void decrClck(View V){
        if(theNumbers[selected] - toSubstract > 0 && toSubstract >= 0 && myTurn && !isAllZero()){toSubstract++;}
        dispNums[selected].setText(Integer.toString((theNumbers[selected]-toSubstract)));
    }

    public void computersMove(){
        Random random = new Random();
        if(difficulty == 0){
            int r = random.nextInt(10);
            if(r > 5){
                proMove();
            }
            else{
                randMove();
            }
        }
        if(difficulty == 1){
            int r = random.nextInt(10);
            if(r > 3){
                proMove();
            }
            else{
                randMove();
            }
        }
        if(difficulty == 2){
            proMove();
        }
    }

    public void proMove(){
        int temp = 0;
        for(int i = 0; i < 5; i++){
            temp = temp^theNumbers[i];
        }
        for(int i = 0; i < 5; i++){
            if(theNumbers[i] > 0){
                int temp2 = theNumbers[i]^temp;
                if(temp2 < theNumbers[i]){
                    theNumbers[i] = temp2;
                    updateDisp();
                    myTurn = true;
                    break;
                }
            }
        }
        if(isAllZero() && myTurn){
            //---- Gép nyert
            lose.setVisibility(View.VISIBLE);
        }
        if(!myTurn){randMove();}
    }

    public void randMove(){
        Random random = new Random();
        if(!isAllZero()){
            for(int i = 0; i < 5; i++){
                if(theNumbers[i] > 0){
                    theNumbers[i] = random.nextInt(theNumbers[i]);
                    updateDisp();
                    myTurn = true;
                    break;
                }
            }
            if(isAllZero() && myTurn){
                //---- Gép nyert
                lose.setVisibility(View.VISIBLE);
            }
        }
        else{
            //---- Játékos nyert
            win.setVisibility(View.VISIBLE);
        }
    }

    public boolean isAllZero(){
        boolean c = true;
        for(int i = 0; i < 5; i++){
            if(theNumbers[i] > 0){c = false; break;}
        }
        return c;
    }
}