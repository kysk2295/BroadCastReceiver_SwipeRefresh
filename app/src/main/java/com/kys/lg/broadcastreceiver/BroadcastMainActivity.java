package com.kys.lg.broadcastreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BroadcastMainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    Button btn_start,btn_stop;
    static int badgeCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_main);

        btn_start=findViewById(R.id.btn_start);
        btn_stop=findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(click);
        btn_stop.setOnClickListener(click);

    }

    View.OnClickListener click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent receiverIntent= null;
            PendingIntent pendingIntent= null;

            switch (view.getId()){

                case R.id.btn_start:
                    alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                    //화면 전환이 아님(신호전달용 인텐트)
                    receiverIntent=new Intent(BroadcastMainActivity.this,MyReceiver.class);

                    pendingIntent=PendingIntent.getBroadcast(BroadcastMainActivity.this,0,receiverIntent,PendingIntent.FLAG_UPDATE_CURRENT);


                    ////RTC_WAKEUP: UTC표준시간을 기준으로 장치를 깨움
                    alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,  //알람 타입
                            SystemClock.elapsedRealtime(),  //첫 알람 발생시간
                            6000,  //알람 반복시간
                            pendingIntent);     //알람 실행시 호출될 pendingIntent

                    break;
                case R.id.btn_stop:
                    alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                    //화면 전환이 아님(신호전달용 인텐트)
                    receiverIntent=new Intent(BroadcastMainActivity.this,MyReceiver.class);

                    pendingIntent=PendingIntent.getBroadcast(BroadcastMainActivity.this,0,receiverIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.cancel(pendingIntent);
                    break;
            }

        }
    };
}
