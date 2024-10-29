package com.zjtec.travel.vo;

public class ResMsg {
    private String errcode="0";
    private String errmsg;
    private Object result;

    public ResMsg(){

    }

    public ResMsg(String errcode,String errmsg){
        this.errcode=errcode;
        this.errmsg=errmsg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
