package com.professionalblog.gamerblog.services.Exception;

import java.io.Serial;

public class PostNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public PostNotFoundException(Object id) {
        super("Post not found. Id: " +id);
    }
}
