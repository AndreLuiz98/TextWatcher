package com.ifpb.AppAndroid.textwatcher.util;

public class Response {

    private int CodigoHttp;

    private String contentValue;

    public Response(int statusCodeHttp, String contentValue) {

        this.CodigoHttp = statusCodeHttp;
        this.contentValue = contentValue;
    }

    public int getStatusCodeHttp() {
        return CodigoHttp;
    }

    public void setStatusCodeHttp(int statusCodeHttp) {
        this.CodigoHttp = statusCodeHttp;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

}
