package com.microwise.halley.bean.vo;

/**
 * 用户实体对象
 *
 * @author li.jianfei
 * @date 2013-11-12
 */
public class UserVO {

    /**
     * 用户ID
     */
    private int id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 手机
     */
    private String mobile;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
