package com.webank.wecube.plugins.bdp.common;

public class BdpException extends RuntimeException {

    public BdpException(String message) {
        super(message);
    }

    public BdpException(String message, Throwable cause) {
        super(message, cause);
    }
}
