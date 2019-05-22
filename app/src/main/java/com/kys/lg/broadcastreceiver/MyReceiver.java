package com.kys.lg.broadcastreceiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //외부에서 브로드캐스트를 요청하면 실행되는 메서드
        //알림 객체 만들어서 작업함.

        String chaanelId="channel";
        String channelName="Channel Name";

        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //오레오 버전 이상부터는 알림을 전송할 채널이 필요
        //O가 오레오 버전을 뜻한다.
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            //중요도가 높을 수록 알림 상단에 위치
            int importance=NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(chaanelId,channelName,importance);

            manager.createNotificationChannel(mChannel);

        }
        //버전과 상관없이 공통
        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,chaanelId);

        Intent notificationIntent= new Intent(context,BroadcastMainActivity.class); //context: 현재 화면에서 메인 액티비티로
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //인텐트 정보를 위임해서 가지게 될 PendingIntent (알림쪽에서 필수)
        //FLAG_UPDATE_CURRENT: 이미 인텐트가 실행중인 경우 내용만 갱신
        //FLAG_ON_SHOT: 인텐트를 한번만 실행되게 한다.
        //FLAG_CANCEL_CURRENT: 이미 실행중인 인텐트를 취소하고 새로 생성
        //FLAG_NO_CREATE: 실행중인 인텐트가 없다면 새로 생성하지 않는다.
        PendingIntent pendingIntent=    PendingIntent.getActivity(context,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        //알림 생성
        builder.setContentTitle("알림 제목")
                .setContentText("알림 내용")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(new long[]{0,100,500,100}) //0:첫 진동 전 대기시간 100:진동 세기,500: 2번째 진동전 대기시간,100: 진동세기
                .setContentIntent(pendingIntent);

        //아이콘 뱃지 설정
        ShortcutBadger.applyCount(context,++BroadcastMainActivity.badgeCount);

        //매니저를 통해 알림 전송
        manager.notify(0,builder.build());


        // manager.cancel(0);

    }//onReceive()

}
