package com.application.boxmadikv1.dao;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.List;

public abstract class BaseDaoImpl<T extends BaseModel, Q extends ModelAdapter<T>> implements BaseDao<T> {

    public static final String TAG = BaseDaoImpl.class.getSimpleName();

    protected abstract Class<T> getEntityClass();

    protected abstract Class<Q> getTableclass();

    @Override
    public T get(String id) {
        return getWithQuery(getDefaultSqlOperator().eq(id));
    }

    private Property<String> getDefaultSqlOperator() {
        return new Property<>(getTableclass(), "key");
    }

    @Override
    public List<T> getAll() {
        return SQLite.select()
                .from(getEntityClass())
                .queryList();
    }


    @Override
    public boolean create(T entity) {
        if (entity instanceof BaseEntity) {
            Log.d(TAG, "Guardando BD ");
            ((BaseEntity) entity).setSyncFlag(BaseEntity.FLAG_ADDED);
            // return entity.save();
        } else {
            Log.d(TAG, "Probelmas al guardarGuardando BD ");
            //return entity.save();
        }
        return entity.save();
        /*if (entity instanceof BaseEntity) {
            ((BaseModel) entity).setSyncFlag(BaseEntity.FLAG_ADDED);
        } else {
            Log.d(TAG, "Cuando trabajaremos 2 entidadesTable");
            //  ((BaseRelEntity) entity).setSyncFlag(BaseRelEntity.FLAG_ADDED);
        }*/
        //return entity.save();
    }
/*
    @Override
    public boolean update(T entity) {
        return false;
    }

    @Override
    public boolean remove(T entity) {
        return false;
    }*/


    @Override
    public T getWithQuery(SQLOperator sqlOperator) {
        return SQLite.select()
                .from(getEntityClass())
                .where(sqlOperator)
                .querySingle();
    }

    @Override
    public List<T> getListWithQuery(SQLOperator... operators) {
        return SQLite
                .select()
                .from(getEntityClass())
                .where(operators)
                .queryList();
    }


    public interface Callback<T> {
        void onSuccess(T result);

        void onError(Throwable error);
    }
}
