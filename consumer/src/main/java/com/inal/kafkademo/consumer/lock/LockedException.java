package com.inal.kafkademo.consumer.lock;

public class LockedException extends RuntimeException {

    public LockedException() {
        super("Process has been locked before");
    }
}