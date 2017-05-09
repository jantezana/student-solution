package com.truextend.studentsolution.services;

/**
 * Service exception class.
 *
 * @author jantezana
 * @version 2017-05-08
 */
public class ServiceException extends Exception {

    /**
     * Creates an instance of {@link com.truextend.studentsolution.services.ServiceException}
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Creates an instance of {@link com.truextend.studentsolution.services.ServiceException}
     *
     * @param message the message
     * @param cause   the cause
     */
    public ServiceException(String message,
                            Throwable cause) {
        super(message, cause);
    }
}
