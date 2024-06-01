package org.buelna.jpaonetoone.exceptions;

public class MethodNotAllowedException extends RuntimeException {

    public MethodNotAllowedException(String message) {
        super(message);
    }

    public MethodNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
