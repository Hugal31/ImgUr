package com.github.Hugal31.imgur;

public class ImgurException extends Exception {

    public ImgurException(String msg) {
        super(msg);
    }

    public ImgurException(String msg, Throwable parent) {
        super(msg, parent);
    }

    public ImgurException(Throwable parent) {
        super(parent);
    }

}
