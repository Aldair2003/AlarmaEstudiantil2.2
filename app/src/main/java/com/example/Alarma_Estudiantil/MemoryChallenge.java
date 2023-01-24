package com.example.Alarma_Estudiantil;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MemoryChallenge extends AppCompatActivity {

    MediaPlayer mp;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12;
    boolean turnOver = false;
    int pooh,mic,goofy,lionking,pinocio, mini,logo;
    int lastClicked = -1;
    int clicked = 0;
    int Score = 0;
    int Minute = 120 ;
    boolean done;
    PendingIntent pendingIntent = null;
    AlarmManager alarmManager;
    public static final int RECEIVER_REQ = 280192;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorychallenge);

        Log.d("tslil", "Memory Challenge activity");

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        done = false;

        SetUp();

        ArrayList<Integer> images = new ArrayList<>(Arrays.asList(pooh, mic, goofy, lionking, pinocio, mini, pooh, mic, goofy, lionking, pinocio, mini));
        ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12));

        Collections.shuffle(images);

        for (int i = 0; i <= 11; i++) {
            buttons.get(i).setText("cardBack");
            buttons.get(i).setTextSize(0.0F);
            int numI = i;

            buttons.get(i).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    if (buttons.get(numI).getText() == "cardBack" && !turnOver){
                        buttons.get(numI).setBackgroundResource(images.get(numI));
                        buttons.get(numI).setText(images.get(numI));
                        if (clicked == 0){
                            lastClicked = numI;
                        }
                        clicked++;
                    }

                    else if (buttons.get(numI).getText() != "cardBack" ){
                        buttons.get(numI).setBackgroundResource(logo);
                        buttons.get(numI).setText("cardBack");

                        clicked--;
                    }

                    if (clicked == 2) {
                        turnOver = true;

                        if (buttons.get(numI).getText() == buttons.get(lastClicked).getText()) {
                            buttons.get(numI).setEnabled(false);
                            buttons.get(lastClicked).setEnabled(false);
                            turnOver = false;//לא ניתן להפוך את התמונות כאשר יש זוג
                            clicked = 0;
                            Score++;

                            if(Score == 6){
                                done = true;
                                displayToastCorrect();
                                Intent intent1 = new Intent(v.getContext(), AlarmActivity.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                MemoryChallenge.this.startActivity(intent1);
                                finish();
                            }
                        }
                    }

                    else if (clicked == 0){
                        turnOver = false;
                    }
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mp = MediaPlayer.create(this,R.raw.dbz);
        mp.setLooping(true);
        mp.start();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()) {
            mp.stop();
        }

        if(!done){
            Intent intent = new Intent(this, MyReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(
                    this, RECEIVER_REQ, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (Minute * 1000), pendingIntent);
        }
        mp.release();

    }

    private void SetUp() {
        //יצירת הכפתורים
        btn1 = findViewById(R.id.btn1); btn2 = findViewById(R.id.btn2); btn3 = findViewById(R.id.btn3); btn4 = findViewById(R.id.btn4); btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6); btn7 = findViewById(R.id.btn7); btn8 = findViewById(R.id.btn8); btn9 = findViewById(R.id.btn9); btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11); btn12 = findViewById(R.id.btn12);
        //יצירת משתנה לכל תמונה
        logo = R.drawable.logo; pooh = R.drawable.pooh; mic =  R.drawable.mic; goofy = R.drawable.goofy;
        lionking = R.drawable.lionking; pinocio = R.drawable.pinocio; mini = R.drawable.mini;
    }

    private void displayToastCorrect() {
        Toast.makeText(this, "Bien hecho!", Toast.LENGTH_SHORT).show();
    }
}