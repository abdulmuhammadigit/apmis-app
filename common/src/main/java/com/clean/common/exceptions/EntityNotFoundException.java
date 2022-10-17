package com.clean.common.exceptions;

public class EntityNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String entity) {
        super("Entity Of Type "+entity+" Does Not Exists!");
    }
    public EntityNotFoundException(String entity,Object value){
        super("Entity Of Type "+entity+" With Key ["+value+"] Does Not Exists!");
    }
}
