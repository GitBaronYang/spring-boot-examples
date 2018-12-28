package com.lxg.springboot;


/**
 * @author 
 * 存储token值
 *
 */
public class Token {
	public static String token = null;
	
	private Userinfo userinfo;
    public Userinfo getUserinfo() {
        return userinfo;
    }
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
        token = userinfo.getToken();
    }
    @Override
    public String toString() {
        return "Token [userinfo=" + userinfo + "]";
    }
	
}

class Userinfo{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "userinfo [token=" + token + "]";
    }
    
}
