package com.rockettravel.app.util;

public class CashRegistryException extends Exception {

    public CashRegistryException(final String message) {
        super(message);
    }

    public CashRegistryException(final String message, final Exception e) {
        super(message, e);
    }

    public CashRegistryException(final Exception e) {
        super(e);
    }

}
