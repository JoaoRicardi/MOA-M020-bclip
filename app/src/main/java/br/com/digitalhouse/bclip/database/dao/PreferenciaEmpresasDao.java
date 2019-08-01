package br.com.digitalhouse.bclip.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import io.reactivex.Flowable;


@Dao
public interface PreferenciaEmpresasDao {

    @Query("SELECT * FROM preferenciaEmpresas")
    Flowable<List<PreferenciaEmpresas>> getAll();

    @Insert
    void inserir(PreferenciaEmpresas preferenciaEmpresas);

    @Delete
    void delete(PreferenciaEmpresas preferenciaEmpresas);

    @Update
    void update(PreferenciaEmpresas preferenciaEmpresas);



}
