package com.bestv.sdk.bean;


import java.io.Serializable;

/**
 * 对外接口信息封装.
 *
 * @author <a href="http://www.jiangzezhou.com">jiangzezhou</a>
 * @version 1.0.0.0, 6/13/15 16::33
 */
public class ExtResponseInfo implements Serializable {

    private int code;

    private String msg;

    /**
     * 数据信息,�?般为json解构.
     */
    private String data;


    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


	@Override
	public String toString() {
		return "ExtResponseInfo [code=" + code + ", msg=" + msg + ", data="
				+ data + "]";
	}
    
}
