package com.my.studyandroid;


import net.oschina.app.AppContext;
import net.oschina.app.common.UIHelper;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.RadioButton;

public class MainActivity extends Activity {

	private RadioButton fbNews;
	private RadioButton fbQuestion;
	private AppContext  appContext;
	
	private TweetReceiver tweetReceiver;// 动弹发布接收器
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
		
		
		// 注册广播接收器
		tweetReceiver = new TweetReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("net.oschina.app.action.APP_TWEETPUB");
		registerReceiver(tweetReceiver, filter);
		
		appContext = (AppContext) getApplication();
		
		// 网络连接判断
		if(!appContext.isNetworkConnected())
			UIHelper.ToastMessage(this, R.string.network_not_connected);
			
				
		fbNews = (RadioButton) findViewById(R.id.main_footbar_news);
		fbQuestion = (RadioButton) findViewById(R.id.main_footbar_question);
		
		
		/*fbNews.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean bcheck = fbNews.isChecked();
				fbNews.setChecked(!fbNews.isChecked());
			}
		});
		
		fbQuestion.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				fbQuestion.setChecked(!fbQuestion.isChecked());
			}
		});*/
		
	}
	
	protected void onResume(){
		super.onResume();
		fbNews.setChecked(true);
		fbQuestion.setChecked(false);
	}
	
	public class TweetReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(final Context context, Intent intent) {
			
		}
	}


}
