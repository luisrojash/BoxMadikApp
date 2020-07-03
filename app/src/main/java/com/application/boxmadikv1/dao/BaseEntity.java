package com.application.boxmadikv1.dao;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.structure.BaseModel;


import java.util.Date;

public class BaseEntity extends BaseModel {

    public static final int FLAG_EXPORTED = 4;
    public static final int FLAG_ADDED = 1;
    public static final int FLAG_UPDATED = 2;
    public static final int FLAG_DELETED = 3;
    public static final int FLAG_ERROREXPORTED = 5;

    @Column
    public int syncFlag;
    @Column
    public long fechaCreacion;
    @Column
    public long fechaModificacion;
    @Column
    public long timestampFlag;

    public int getSyncFlag() {
        return syncFlag;
    }

    public BaseEntity() {
    }

    public void setSyncFlag(int syncFlag) {
        this.syncFlag = syncFlag;
        switch (syncFlag) {
            case FLAG_ADDED:
                this.fechaCreacion = getTime();
                this.fechaModificacion = getTime();

                break;
            case FLAG_UPDATED:
                this.fechaModificacion = getTime();
                break;
        }

        this.timestampFlag = getTime();
    }

    public long getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(long fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public long getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(long fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public long getTimestampFlag() {
        return timestampFlag;
    }

    public void setTimestampFlag(long timestampFlag) {
        this.timestampFlag = timestampFlag;
    }

    public static long getTime() {
        return new Date().getTime();
    }

}
