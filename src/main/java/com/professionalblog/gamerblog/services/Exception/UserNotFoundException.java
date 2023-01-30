package com.professionalblog.gamerblog.services.Exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Object id) {
        super("User not found. Id: " +id);
    }
}
