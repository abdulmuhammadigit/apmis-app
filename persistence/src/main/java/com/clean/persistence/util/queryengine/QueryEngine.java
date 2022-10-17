package com.clean.persistence.util.queryengine;


import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

public class QueryEngine {
    private EntityManager entityManager;
    private String query;
    private String from;
    private List<String> selects;
    private List<String> conditions;
    private List<Object> conditionsValue;
    private List<String> conditionsValueParameter;
    private List<String> joins;
    private String model;
    private List<String> orderBys;
    private String orderByType;
    private int offsetStart;
    private int limit;
    private HashMap<String,List<Object>> inConditions;
    private List<Object> ins;
    private boolean distinct;

    //constructor section
    public QueryEngine(EntityManager manager){
        this.entityManager = manager;
        this.query= "";
        this.from= "";
        this.selects = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.conditionsValue = new ArrayList<>();
        this.conditionsValueParameter = new ArrayList<>();
        this.joins = new ArrayList<>();
        this.model = "";
        this.orderBys = new ArrayList<>();
        this.orderByType = "asc";
        this.offsetStart = 0;
        this.limit = 50;
        this.inConditions = new HashMap<>();
        this.ins= new ArrayList<>();
        this.distinct=false;
    }
    //entity section
    public QueryEngine from(String entity,String alias){
        this.from = entity.concat(" ").concat(alias);
        return this;
    }
    //where section
    public QueryEngine where(String whereCondition, String field,String conditionType, Object conditionValue){
        StringBuilder stringBuilder = new StringBuilder();
        if(WhereConditionType.AND.equals(whereCondition) || WhereConditionType.OR.equals(whereCondition))
            if(conditions.size() > 0)
                stringBuilder.append(" "+ whereCondition+ " ");

        stringBuilder.append(field);
        stringBuilder.append(getConditionFormat(field ,conditionType,conditionValue));
        this.conditions.add(stringBuilder.toString());
        return this;
    }

    private String getConditionFormat(String field , String conditionType, Object conditionValue){

        field =field.replace(".","");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");

        this.conditionsValueParameter.add(field);

        if(conditionType.equals(ConditionType.LIKE_START_WHIT)){
            stringBuilder.append("like :").append(field);
            this.conditionsValue.add("%"+conditionValue);
        }
        else if(conditionType.equals(ConditionType.LIKE_END_WITH)){
            stringBuilder.append("like :").append(field);
            this.conditionsValue.add("%"+conditionValue);
        }
        else if(conditionType.equals(ConditionType.LIKE_ANY_POSITION)){
            stringBuilder.append("like :").append(field);
            this.conditionsValue.add("%"+conditionValue+"%");
        }else if(conditionType.equals(ConditionType.IN)){
            stringBuilder.append("in (:").append(field).append(")");
            this.conditionsValue.add(conditionValue);
        }else{
//            if(conditionValue instanceof String || conditionValue instanceof Character){
//                stringBuilder.append(conditionType).append(" ?"+conditionsValue.size());
//                this.conditionsValue.add(conditionValue);
//            }
//            else{
            stringBuilder.append(conditionType).append(" :").append(field);
            this.conditionsValue.add(conditionValue);
            //    }
        }
        return stringBuilder.toString();
    }


    public QueryEngine in(String columnName,String whereCondition,List<Object> values){

        StringBuilder stringBuilder = new StringBuilder();
        if(WhereConditionType.AND.equals(whereCondition) || WhereConditionType.OR.equals(whereCondition))
            if(conditions.size() > 0)
                stringBuilder.append(" "+ whereCondition+ " ");

        this.inConditions.put(columnName.replace(".",""),values);
        stringBuilder.append(" ");
        stringBuilder.append(columnName);
        stringBuilder.append(" in( :"+columnName.replace(".","")+" )");
        this.conditions.add(stringBuilder.toString());
        return this;
    }

    //join section
    public QueryEngine innerJoin(String joinEntity,String alias,String parentJoinFeild,String joinFeild){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" join ").append(joinEntity).append(" ").append(alias).
                append(" on ").append(parentJoinFeild).append(" = ").append(joinFeild).append(" ");
        joins.add(stringBuilder.toString());
        return this;
    }

    public QueryEngine leftJoin(String joinEntity,String alias,String parentJoinFeild,String joinFeild){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" left join ").append(joinEntity).append(" ").append(alias).
                append(" on ").append(parentJoinFeild).append(" = ").append(joinFeild).append(" ");
        joins.add(stringBuilder.toString());
        return this;
    }

    public QueryEngine leftJoin(String joinEntity, String alias, Collection<JoinCondition> conditions){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" left join ").append(joinEntity).append(" ").append(alias).append(" on ");
        boolean isFirst = true;
        for(JoinCondition condition :conditions){
            if(!isFirst){
                stringBuilder.append(" and ");
            }
            if(condition.value != null){
                stringBuilder.append(condition.parentField).append(" = ").append(condition.value).append(" ");
            }
            else{
                stringBuilder.append(condition.parentField).append(" = ").append(condition.entityField).append(" ");
            }
            isFirst = false;
        }
        joins.add(stringBuilder.toString());
        return this;
    }

    public QueryEngine rightJoin(String joinEntity,String alias,String parentJoinFeild,String joinFeild){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" right join ").append(joinEntity).append(" ").append(alias).
                append(" on ").append(parentJoinFeild).append(" = ").append(joinFeild).append(" ");
        joins.add(stringBuilder.toString());
        return this;
    }

    //selects section
    public QueryEngine addSelect(String selectName){
        this.selects.add(selectName);
        return this;
    }

    // order by and limitation section
    public QueryEngine orderByDesc(String columnName){
        orderBys.add(columnName);
        this.orderByType = "desc";
        return this;
    }

    public QueryEngine orderByAsc(String columnName){
        orderBys.add(columnName);
        this.orderByType = "asc";
        return this;
    }

    public QueryEngine distinct(boolean distinct){
        this.distinct =distinct;
        return this;
    }

    public QueryEngine offSet(int start,int limit){
        this.offsetStart = --start;
        this.limit = limit;
        return  this;
    }

    public QueryEngine limit(int limit){
        this.limit = limit;
        return this;
    }

    //execution section
    public <T> List<T>  execute(Class<T> type ) {
        this.model=type.getCanonicalName();
        TypedQuery<T> queryResult= entityManager.createQuery(this.constructQuery(),type);

        // all conditions except in
        for(int i=0; i<conditionsValue.size() ; i++){
            queryResult.setParameter(conditionsValueParameter.get(i),conditionsValue.get(i));
        }

        // in conditions
        for(Map.Entry<String, List<Object>> entry : inConditions.entrySet()) {
            String key = entry.getKey();
            List<Object> value = entry.getValue();
            queryResult.setParameter(key,value);
        }
        List<T> resultList=queryResult.setFirstResult(offsetStart).setMaxResults(limit).getResultList();
        return resultList;
    }

    public void clear(){
        this.query= "";
        this.from= "";
        this.selects.clear();
        this.conditions.clear();
        this.conditionsValue.clear();
        this.conditionsValueParameter.clear();
        this.joins.clear();
        this.model = "";
        this.orderBys.clear();
        this.orderByType = "asc";
        this.offsetStart = 0;
        this.limit = 50;
        this.inConditions.clear();
        this.ins.clear();
        this.distinct=false;
    }

    //construct query section
    private String constructQuery(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        if(distinct)
            stringBuilder.append(" distinct ");

        stringBuilder.append(" new " + model);
        stringBuilder.append(" ( "+ this.getSelectList() +" ) ");
        stringBuilder.append(" from " +from + " ");
        stringBuilder.append(getJoinList());
        if(conditions.size() > 0 || inConditions.size() > 0)
            stringBuilder.append(" where ");
        stringBuilder.append(this.getConditionList());
        stringBuilder.append(" order by " + getOrderByLIst() + " ");
        query = stringBuilder.toString();
        System.out.println(query);
        return query;
    }

    //model parameters
    private String getSelectList(){
        StringBuilder stringBuilder = new StringBuilder();
        selects.forEach(e->stringBuilder.append(e.toString() + " ,"));
        return stringBuilder.replace(stringBuilder.length()-1,stringBuilder.length(),"").toString();
    }

    //get condition list
    private String getConditionList(){
        StringBuilder stringBuilder = new StringBuilder();
        conditions.forEach(e->stringBuilder.append(e.toString() + " "));
        return stringBuilder.toString();
    }

    //get joinlist
    private String getJoinList(){
        StringBuilder stringBuilder = new StringBuilder();
        joins.forEach(e->stringBuilder.append(e.toString() + " "));
        return  stringBuilder.toString();
    }

    //get order by list
    private String getOrderByLIst(){
        StringBuilder stringBuilder = new StringBuilder();
        if(orderBys.size() == 0){
            stringBuilder.append(selects.get(0) + " ");
        }else{
            orderBys.forEach(e->stringBuilder.append(e.toString()+ " ,"));
        }
        stringBuilder.replace(stringBuilder.length()-1,stringBuilder.length(),"");
        stringBuilder.append(" " + orderByType + " ");
        return stringBuilder.toString();
    }
}
