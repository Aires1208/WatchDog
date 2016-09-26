package com.zte.ums.watchdog.common.exception;

/**
 * Created by root on 2016/9/20.
 */
public class WatchDogException extends RuntimeException {

    public WatchDogException() {
        super();
    }

    public WatchDogException(String message) {
        super(message);
    }

    public WatchDogException(String message, Throwable cause) {
        super(message, cause);
    }

    public WatchDogException(Throwable cause) {
        super(cause);
    }

}
