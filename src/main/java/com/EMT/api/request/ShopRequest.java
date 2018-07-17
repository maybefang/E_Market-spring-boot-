package com.EMT.api.request;

import javax.validation.constraints.NotBlank;

//够短从前端接数据的格式
public class ShopRequest {

    @NotBlank(message = "商铺名不能为空")
    private String sname;

    private String spwd;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpwd() {
        return spwd;
    }

    public void setSpwd(String spwd) {
        this.spwd = spwd;
    }
}
