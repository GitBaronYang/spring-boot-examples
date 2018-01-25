package com.yang.basicmodel.entity.common;

/**
 * @author yf 生成token所需要的信息
 * @date 创建时间：2017年12月2日 上午10:52:48
 * @version 1.0
 */
public class WebToken {
    // base64位签名
    private String salt;
    // 发行人名称
    private String name;
    // 保存时间
    private int expiresSecond;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpiresSecond() {
        return expiresSecond;
    }

    public void setExpiresSecond(int expiresSecond) {
        this.expiresSecond = expiresSecond;
    }

    @Override
    public String toString() {
        return "WebToken [salt=" + salt + ", name=" + name + ", expiresSecond=" + expiresSecond + "]";
    }
    
}
