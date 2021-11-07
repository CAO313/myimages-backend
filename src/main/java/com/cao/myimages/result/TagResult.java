package com.cao.myimages.result;

import java.io.Serializable;

public class TagResult implements Serializable {
    private boolean flag;
    private String message;
    Object data;
    public TagResult(boolean flag, String message, Object data){
        this.flag = flag;
        this.data =data;
        this.message = message;
    }
    public TagResult(boolean flag, String message){
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
