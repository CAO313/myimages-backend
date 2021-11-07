package com.cao.myimages.result;

import java.io.Serializable;

public class MusicResult implements Serializable {
    private boolean flag;
    private String message;
    Object data;
    public MusicResult(boolean flag, String message, Object data){
        this.flag = flag;
        this.data =data;
        this.message = message;
    }
    public MusicResult(boolean flag, String message){
        this.flag = flag;
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
