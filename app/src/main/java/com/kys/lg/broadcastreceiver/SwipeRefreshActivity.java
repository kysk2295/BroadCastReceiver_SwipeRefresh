package com.kys.lg.broadcastreceiver;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SwipeRefreshActivity extends AppCompatActivity {

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        refreshLayout=findViewById(R.id.swipe);

        //디스크 배경색 변경
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.argb(255,127,179,255));

        //디스크(로딩) 사이즈 변경 (DEFAULT/LARGE)
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);

        //디스크 위치
        //refreshLayout.setProgressViewEndTarget(true,300);
        refreshLayout.setProgressViewOffset(true,100,300);
        //디스크 안에 있는 그거,, 까만거 막 돌아가는 프로그레스 새깔 마꾸는 메서드
        refreshLayout.setColorSchemeResources(R.color.color1,R.color.color2,R.color.color3,R.color.color4);

        //감지자 등록
        refreshLayout.setOnRefreshListener(refreshListener);

    }//onCreate()

    //감지자 생성
    SwipeRefreshLayout.OnRefreshListener refreshListener= new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            //로딩시작시 호출되는 메서드
            handler.sendEmptyMessageDelayed(0,3000);//3초 뒤에 핸들러 소환
        }
    };

    Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {

            //로딩 완료시 디스크 제거
            refreshLayout.setRefreshing(false);

        }
    };

}
