package com.clean.persistence.util.queryengine;

public class JoinCondition {
    public String parentField;
    public String entityField;
    public Object value;

    public JoinCondition(String parentField, String entityField) {
        this.parentField = parentField;
        this.entityField = entityField;
    }

    public JoinCondition(String parentField, Object val) {
        this.parentField = parentField;
        this.value = val;
    }
}