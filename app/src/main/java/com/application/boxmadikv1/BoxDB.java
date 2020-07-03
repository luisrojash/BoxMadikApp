package com.application.boxmadikv1;

import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = BoxDB.NAME, version = BoxDB.VERSION, insertConflict = ConflictAction.REPLACE)
public class BoxDB {
    public static final String NAME = "BoxDB";
    public static final int VERSION = 1;
}
