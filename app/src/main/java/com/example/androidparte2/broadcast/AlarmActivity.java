package com.example.androidparte2.broadcast;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class AlarmActivity extends Activity {
    private static final int TEMPO = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = new TextView(this);
        text.setText("Alarme para " + TEMPO + " segundos a partir de agora");
        tarefa(TEMPO);
    }

    private void tarefa(int tempo) {
        Intent it = new Intent("ALARM_EXECUTE");
        PendingIntent pi = PendingIntent.getBroadcast(AlarmActivity.this, 0, it, PendingIntent.FLAG_MUTABLE);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, tempo);
        //Gestor de agendamentos do SO
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long time = c.getTimeInMillis();
        //Acorda o dispositivo e vibra
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pi);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent it = new Intent("ALARM_EXECUTE");
        PendingIntent pi = PendingIntent.getBroadcast(AlarmActivity.this, 0, it, PendingIntent.FLAG_MUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pi);
    }
}