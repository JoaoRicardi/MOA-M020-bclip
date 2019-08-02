package br.com.digitalhouse.bclip.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
