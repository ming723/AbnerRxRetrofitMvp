/**
 *
 * FileName：MD5Util.java
 *
 * Description：MD5校验码生成工具
 */

package com.abner.ming.vipmvp.rxretrofit.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * MD5校验码生成工具
 * 
 */
public class Md5Util {
	/**
	 * 生成md5校验码
	 * 
	 * @param srcContent
	 *            需要加密的数据
	 * @return 加密后的md5校验码。出错则返回null。
	 */
	public static String makeMd5Sum(byte[] srcContent) {
		if (srcContent == null) {
			return null;
		}

		String strDes = null;

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(srcContent);
			strDes = bytes2Hex(md5.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	private static String bytes2Hex(byte[] byteArray) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (byteArray[i] >= 0 && byteArray[i] < 16) {
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}

	/**
	 * 新的md5签名，首尾放secret。
	 * 
	 * @param params
	 *            传给服务器的参数
	 * 
	 * @param secret
	 *            分配给您的APP_SECRET
	 */
	public static String md5Signature(TreeMap<String, String> params,
									  String secret) {
		String result = null;
		StringBuffer orgin = null;
		try {
			orgin = getBeforeSign(params, new StringBuffer(secret));
		} catch (UnsupportedEncodingException e1) {
			throw new RuntimeException("sign encode error !");
		}
		if (orgin == null)
			return result;
		orgin.append(secret);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			result = bytes2Hex(md.digest(orgin.toString().getBytes("utf-8")));
		} catch (Exception e) {
			throw new RuntimeException("sign error !");
		}
		return result;
	}

	/**
	 * 添加参数的封装方法
	 * 
	 * @param params
	 * @param orgin
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static StringBuffer getBeforeSign(TreeMap<String, String> params,
											  StringBuffer orgin) throws UnsupportedEncodingException {
		if (params == null)
			return null;
		Map<String, String> treeMap = new TreeMap<String, String>();
		treeMap.putAll(params);
		Iterator<String> iter = treeMap.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			String value = params.get(name);
			name = URLEncoder.encode(name, "utf-8");
			value = URLEncoder.encode(value, "utf-8");
			orgin.append(name).append(value);
		}
		return orgin;
	}

}
