package com.zaoanx.pojo;

import android.graphics.drawable.Drawable;

/**
 * 应用程序信息(icon,name,packageName,versionName,versionCode)
 * @Date 2014-9-18
 * @author zaoanx
 *
 */
public class AppInfo {

	private Drawable appIcon;
	private String appName;
	private String appPackageName;
	private String appVersionName;
	private int appVersionCode;
	
	public Drawable getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getAppPackageName() {
		return appPackageName;
	}
	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}
	
	public String getAppVersionName() {
		return appVersionName;
	}
	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}
	
	public int getAppVersionCode() {
		return appVersionCode;
	}
	public void setAppVersionCode(int appVersionCode) {
		this.appVersionCode = appVersionCode;
	}
	@Override
	public String toString() {
		return appName+"\n";
	}
}
