package com.clean.common.exceptions;

public class BussinessRuleException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BussinessRuleException() {
        super();
    }

    public BussinessRuleException(String error) {
        super(error);
    }
    
}
