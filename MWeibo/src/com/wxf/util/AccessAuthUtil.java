package com.wxf.util;

import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;

public class AccessAuthUtil {

	private static Weibo weibo = null;
	private static RequestToken requestToken;

	static {
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret",
				Weibo.CONSUMER_SECRET);
		weibo = new Weibo();
		try {
			requestToken = weibo.getOAuthRequestToken();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return ·µ»ØÊÚÈ¨µÄurl
	 */
	public static String getAuthorizationURL() {
		String url = null;
		url = requestToken.getAuthenticationURL();
		return url;
	}

	public static AccessToken getAccessToken(String pin) {
		AccessToken accessToken = null;
		try {
			accessToken = requestToken.getAccessToken(pin);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accessToken;
	}
}
