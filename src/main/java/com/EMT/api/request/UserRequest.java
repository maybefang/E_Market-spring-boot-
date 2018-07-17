package com.EMT.api.request;

public class UserRequest {
    //@NotBlank(message = "用户名不能为空")
    private String uname;

    //@NotBlank(message = "密码不能为空")
    private String upwd;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
}
