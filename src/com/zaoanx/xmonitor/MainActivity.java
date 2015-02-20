package com.zaoanx.xmonitor;

import java.util.ArrayList;
import java.util.List;

import com.zaoanx.pojo.AppInfo;
import com.zaoanx.utils.KillAppUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	
	private final static String TAG = "MainActivity";
	
	private TextView tvAppInfoList;
	private Button btForceClose;
	
	private ArrayList<AppInfo> appList;
	
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				btForceClose.setText("Finish");
				btForceClose.setClickable(false);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvAppInfoList = (TextView) findViewById(R.id.tv_app_info_info);
		appList = getAppList();
		tvAppInfoList.setText(appList.toString());
		btForceClose = (Button) findViewById(R.id.bt_force_close);
		btForceClose.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						killAppList();
					}
				}).start();
			}
		});
	}
	
	private ArrayList<AppInfo> getAppList () {
		ArrayList<AppInfo> appList = new ArrayList<AppInfo>();
		List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
		for(int i=0;i<packages.size();i++) { 
			PackageInfo packageInfo = packages.get(i); 
			AppInfo appInfo =new AppInfo(); 
			appInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(getPackageManager()));
			appInfo.setAppName(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
			appInfo.setAppPackageName(packageInfo.packageName);
			appInfo.setAppVersionName(packageInfo.versionName);
			appInfo.setAppVersionCode(packageInfo.versionCode);
			
			if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==0) {
				appList.add(appInfo);
			}
		}
		return appList;
	}
	
	private void killAppList () {
		for(AppInfo appInfo : appList) {
			String packageName = appInfo.getAppPackageName();
			if (!packageName.equals("com.zaoanx.xmonitor")) {
				Log.i(TAG, packageName);
				KillAppUtil.kill(packageName);
			}
		}
		Message message = new Message();
		message.what = 0;
		mHandler.sendMessage(message);

	}
}

