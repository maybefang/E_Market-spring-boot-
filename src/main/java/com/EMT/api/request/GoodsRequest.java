package com.EMT.api.request;

import org.springframework.web.multipart.MultipartFile;

public class GoodsRequest {
    private String gname;

    private Double price;

    private String pic;
    //private MultipartFile pic;

    private Integer num;

    private Integer sid;

    private String msg;


    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public MultipartFile getPic() {
//        return pic;
//    }
//
//    public void setPic(MultipartFile pic) {
//        this.pic = pic;
//    }
}
