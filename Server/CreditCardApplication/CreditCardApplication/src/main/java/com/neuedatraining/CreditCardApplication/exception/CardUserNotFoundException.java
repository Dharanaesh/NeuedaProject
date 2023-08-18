package com.neuedatraining.CreditCardApplication.exception;

public class CardUserNotFoundException  extends Exception{

    public CardUserNotFoundException(String message) {
        super(message);
    }
    public CardUserNotFoundException() {
        super();
    }
    public CardUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardUserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CardUserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
