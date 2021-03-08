package com.iqb.been.base;

public class HttpResponseBean<T> {
    private T d;
    private boolean s;
    private String m;

    public T getD() {
        return d;
    }

    public void setD(T d) {
        this.d = d;
    }

    public boolean isS() {
        return s;
    }

    public void setS(boolean s) {
        this.s = s;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
