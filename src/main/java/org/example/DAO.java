package org.example;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO<T extends DBSerializable> {

    private final Class<T> class_;
    private final String dataBaseAssociatedTable;

    DAO(final Class<T> class_){
        this.class_ = class_;
        DataBaseTable annotation;
        if((annotation = class_.getDeclaredAnnotation(DataBaseTable.class)) == null) throw new RuntimeException(
                "No DataBase table for class "+class_.getSimpleName()+". Use @DataBaseTable to it");
        dataBaseAssociatedTable = annotation.name();
    }

    protected final <fieldT> T getByField(Field field, fieldT fieldValue) throws IllegalAccessException, SQLException {
        T newInstance = null;
        try {
            class_.getConstructor().setAccessible(true);
            newInstance = class_.newInstance();
        }catch (ReflectiveOperationException e){
            throw new IllegalAccessException("Impossible to create new instance of "+class_.getSimpleName() +
                    ".\nCheck it's and it's constructor accessibility");
        }
        this.loadByField(newInstance, field, fieldValue);
        return newInstance;
    }

    public void create(T entity) throws IllegalAccessException, SQLException {
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO "+dataBaseAssociatedTable+"(");
        StringBuilder valuesQueryBuilder = new StringBuilder();
        Field[] fields = class_.getDeclaredFields();
        boolean isFirst = true;
        for(Field field : fields){
            DataBaseField annotation;
            if((annotation = field.getDeclaredAnnotation(DataBaseField.class)) != null){
                if(!annotation.isSequence()){
                    if(isFirst){
                        isFirst=false;
                    }else{
                        queryBuilder.append(",");
                        valuesQueryBuilder.append(",");
                    }
                    try {
                        field.setAccessible(true);
                        valuesQueryBuilder.append("'"+field.get(entity)+"'");
                    } catch (IllegalAccessException e) {
                        throw new IllegalAccessException("Impossible to get the access to the " +
                                class_.getSimpleName() + "'s field " + field.getName());
                    }
                    queryBuilder.append(field.getName());
                }
            }
        }
        queryBuilder.append(") VALUES ("+valuesQueryBuilder.toString()+");");
        DataBaseConnection connection = new DataBaseConnection();
        connection.execute(queryBuilder.toString());
    }

    public void update(T entity) throws IllegalAccessException, SQLException {
        Field[] fields = class_.getDeclaredFields();
        Field primaryKey = null;
        StringBuilder query = new StringBuilder("UPDATE " + dataBaseAssociatedTable + " SET ");
        boolean isFirst = true;
        for(Field field : fields){
            DataBaseField annotation;
            if(field.isAnnotationPresent(DataBaseField.class)){
                annotation = field.getDeclaredAnnotation(DataBaseField.class);
                if(annotation.isPrimaryKey()) {
                    primaryKey = field;
                    continue;
                }
                field.setAccessible(true);
                if(isFirst) {
                    isFirst = false;
                }else{
                    query.append(", ");
                }
                query.append(field.getName()).append(" = '").append(field.get(entity)).append("'");
            }
        }
        if(primaryKey == null) throw new RuntimeException("No primary key field in the class");
        primaryKey.setAccessible(true);
        query.append(" WHERE ").append(primaryKey.getName()).append(" = '").append(primaryKey.get(entity)).append("';");
        DataBaseConnection connection = new DataBaseConnection();
        System.out.println(query.toString());
        connection.execute(query.toString());
    }
    protected final <fieldT> void loadByField(T entity, Field field_, fieldT fieldValue) throws SQLException, IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();

        field_.setAccessible(true);
        String query = "SELECT * FROM "+dataBaseAssociatedTable+" WHERE "+field_.getName()+" = '"+fieldValue+"';";
        //System.out.println(query);
        ResultSet set;
        try{
        DataBaseConnection connection = new DataBaseConnection();
        set = connection.executeQuery(query);
        }catch (SQLException e){
            throw new SQLException("DataBase connection is unable");
        }
        if(set.next()) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    //System.out.println(set.);
                    field.set(entity, set.getObject(field.getName()));
                } catch (IllegalAccessException e) {
                    throw new IllegalAccessException("Impossible to get the access to the " +
                            class_.getSimpleName() + "'s field " + field.getName());
                } catch (SQLException e) {
                    throw new SQLException("DataBase reply doesn't contains the coloumn " + field.getName() +
                            "\n while working with class"+class_.getSimpleName()+"\nCheck if class is correct declared");
                }
            }
        }
    }

}
