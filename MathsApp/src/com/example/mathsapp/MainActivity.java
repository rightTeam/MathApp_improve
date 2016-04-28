package com.example.mathsapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private MediaPlayer mPlayer;// 背景音乐

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (mPlayer == null) {
			mPlayer = MediaPlayer.create(this, R.raw.bg_mic);
		}

		mPlayer.start();
		// mPlayer.setLooping(true);//循环播放背景音乐

		super.onResume();
	}

	// 被被覆盖后暂停后
	@Override
	protected void onPause() {
			mPlayer.stop();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer =null;
		}
		
		super.onDestroy();
	}

	public void Start(View view) {

		Intent intent = new Intent(this, PlayActivity.class);
		startActivity(intent);
	}
	
	//再按一次退出程序,重写onKeyDown方法
		private long exitTime = 0;
		 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	            if((System.currentTimeMillis()-exitTime) > 2000){  
	                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
	                exitTime = System.currentTimeMillis();   
	            } else {
	                finish();
	                System.exit(0);
	            }
	            return true;   
	        }
	        return super.onKeyDown(keyCode, event);
	    }

}
