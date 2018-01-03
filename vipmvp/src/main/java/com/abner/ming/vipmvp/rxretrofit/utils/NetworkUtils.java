package com.abner.ming.vipmvp.rxretrofit.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by xiaoming.li on 2016/3/4.
 * 网络工具
 */
public class NetworkUtils {

    public static String networkMessage="网络在开小差，检查后再试吧";
    /**
     * 获取当前网络连接信息
     *
     * @param paramContext
     * @return
     */
    public static NetworkInfo getActiveNetworkInfo(Context paramContext) {
        return ((ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }

    /**
     * 获取当前连接网络类型
     *
     * @param paramContext
     * @return
     */
    public static int getNetworkType(Context paramContext) {
        ConnectivityManager localConnectivityManager = (ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (localConnectivityManager != null) {
            NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
            if (localNetworkInfo != null)
                return localNetworkInfo.getType();
        }
        return -1;
    }

    /**
     * 检测是否使用3G/4G
     *
     * @param paramContext
     * @return
     */
    public static boolean is3Gor4G(Context paramContext) {
        try {
            int i = ((TelephonyManager) paramContext.getSystemService("phone")).getNetworkType();
            if ((i == 8) || (i == 13) || (i == 3) || (i == 15) || (i == 10) || (i == 14) || (i == 6) || (i == 9) || (i == 12))
                return true;
        } catch (Exception localException) {
        }
        return false;
    }

    /**
     * 检测网络类型是否为移动数据连接
     *
     * @param paramNetworkInfo
     * @return
     */
    public static boolean isMobileNetworkInfo(NetworkInfo paramNetworkInfo) {
        return (paramNetworkInfo.getType() == 0) || (50 == paramNetworkInfo.getType());
    }

    /**
     * 检测是否支持网络
     *
     * @param paramContext
     * @return
     */
    public static boolean isNetSupprot(Context paramContext) {
        ConnectivityManager localConnectivityManager = (ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (localConnectivityManager == null) {
            return false;
        }
        try {
            NetworkInfo[] arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
            if (arrayOfNetworkInfo != null) {
                for (int i = 0; i < arrayOfNetworkInfo.length; i++) {
                    NetworkInfo.State localState1 = arrayOfNetworkInfo[i].getState();
                    NetworkInfo.State localState2 = NetworkInfo.State.CONNECTED;
                    if (localState1 == localState2)
                        return true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 检测是否使用移动数据
     *
     * @param paramContext
     * @return
     */
    public static boolean isNetworkAvailable(Context paramContext) {
        NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (localNetworkInfo != null) && (localNetworkInfo.isAvailable());
    }

    /**
     * 检测是否开启wifi
     *
     * @param paramContext
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static boolean isWifiEnabled(Context paramContext) {
        try {
            NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            return localNetworkInfo.getTypeName().toLowerCase().equals("wifi");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    /**
     * 检测是否通过wifi连接
     *
     * @param paramContext
     * @return
     */
    public static boolean isWifiConnected(Context paramContext) {
        NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (localNetworkInfo != null) && (localNetworkInfo.getType() == 1);
    }

    /**
     * 检查是否连接网络
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mobNetInfo != null && mobNetInfo.isConnected()) {
            return true;
        }
        if (wifiNetInfo != null && wifiNetInfo.isConnected()) {
            return true;
        }
        return false;
    }


    public static String getLocalIpAddress(Context context) {
        String ip = getWIFILocalIpAddress(context);
        if (ip != null) {
            return ip;
        }

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return null;
    }

    public static String getWIFILocalIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        if (ipAddress == 0) return null;
        return ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
                + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
    }
}
