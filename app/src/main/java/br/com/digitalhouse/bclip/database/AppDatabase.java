package br.com.digitalhouse.bclip.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.digitalhouse.bclip.database.dao.PreferenciaEmpresasDao;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;

@Database(entities = {PreferenciaEmpresas.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public static String DATABASE_NAME = "bclip_db";


    public abstract PreferenciaEmpresasDao preferenciaEmpresasDao();


}


