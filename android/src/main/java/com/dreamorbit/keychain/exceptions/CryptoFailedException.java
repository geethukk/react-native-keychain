package com.dreamorbit.keychain.exceptions;

public class CryptoFailedException extends Exception {
    public CryptoFailedException (String message) {
        super(message);
    }

    public CryptoFailedException (String message, Throwable t) {
        super(message, t);
    }
}
