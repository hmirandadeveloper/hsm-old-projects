package br.edu.unibratec.pgm.jogomemoria.managers.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class ConnectivityUtil {
	public static boolean isConnected(Context context)
	{
		try
		{
			ConnectivityManager cm = (ConnectivityManager)
					context.getSystemService(Context.CONNECTIVITY_SERVICE);
			
			TelephonyManager mTelephony = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			
			boolean connected;
			
			NetworkInfo info = cm.getActiveNetworkInfo();
			
			if(info == null)
			{
				return false;
			}
			
			int netType = info.getType();
			connected = info.isConnected();
			
			if(netType == ConnectivityManager.TYPE_WIFI)
			{
				return connected;
			}
			else if (netType == ConnectivityManager.TYPE_MOBILE)
			{
				boolean roam = mTelephony.isNetworkRoaming();
				
				return connected && !roam;
			}
		}
		catch(Exception ex)
		{
			return false;
		}
		return false;
	}
}
