package net.oschina.app;

import net.oschina.app.common.StringUtils;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppContext extends Application{

	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	/**
	 * 检测网络是否可用
	 * @return
	 */
	public boolean isNetworkConnected(){
		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
	    return ni != null && ni.isConnectedOrConnecting();
	}
	
	/**
	 * 获取当前网络类型
	 * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
	 */
	public int getNetworkType(){
		int netType = 0;
		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if(networkInfo == null){
			return netType;
		}
		
		int nType = networkInfo.getType();
		if(nType == ConnectivityManager.TYPE_MOBILE){
			String extraInfo = networkInfo.getExtraInfo();
			if(!StringUtils.isEmpty(extraInfo)){
				if(extraInfo.toLowerCase().equals("cmnet")){
					netType = NETTYPE_CMNET;
				}else{
					netType = NETTYPE_CMWAP;
				}
			}
		}else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NETTYPE_WIFI;
		}
		return netType;
	}
	
	
	/**
	 * 初始化用户登录信息
	 */
	public void initLoginInfo() {
		User loginUser = getLoginInfo();
		if(loginUser!=null && loginUser.getUid()>0 && loginUser.isRememberMe()){
			this.loginUid = loginUser.getUid();
			this.login = true;
		}else{
			this.Logout();
		}
	}
	
	/**
	 * 获取登录信息
	 * @return
	 */
	public User getLoginInfo() {		
		User lu = new User();		
		lu.setUid(StringUtils.toInt(getProperty("user.uid"), 0));
		lu.setName(getProperty("user.name"));
		lu.setFace(getProperty("user.face"));
		lu.setAccount(getProperty("user.account"));
		lu.setPwd(CyptoUtils.decode("oschinaApp",getProperty("user.pwd")));
		lu.setLocation(getProperty("user.location"));
		lu.setFollowers(StringUtils.toInt(getProperty("user.followers"), 0));
		lu.setFans(StringUtils.toInt(getProperty("user.fans"), 0));
		lu.setScore(StringUtils.toInt(getProperty("user.score"), 0));
		lu.setRememberMe(StringUtils.toBool(getProperty("user.isRememberMe")));
		return lu;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
