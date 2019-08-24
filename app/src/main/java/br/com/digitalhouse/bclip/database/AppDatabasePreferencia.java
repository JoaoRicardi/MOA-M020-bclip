package br.com.digitalhouse.bclip.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.digitalhouse.bclip.database.dao.PreferenciaDao;
import br.com.digitalhouse.bclip.database.dao.PreferenciaEmpresasDao;
import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;

@Database(entities = {Preferencia.class}, version = 1)

public abstract class AppDatabasePreferencia extends RoomDatabase {

    public static String DATABASE_NAME = "bclip@_db";

    public abstract PreferenciaDao preferenciasDao();

}


