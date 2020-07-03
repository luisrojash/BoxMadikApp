package com.application.boxmadikv1.dao;

import com.raizlabs.android.dbflow.sql.language.SQLOperator;

import java.util.List;

public interface BaseDao<T> {
    T get(String id);

    List<T> getAll();

    boolean create(T entity);

   // boolean update(T entity);

    //boolean remove(T entity);

    T getWithQuery(SQLOperator sqlOperator);

    List<T> getListWithQuery(SQLOperator... operators);
}
