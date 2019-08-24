package br.com.digitalhouse.bclip.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;
import br.com.digitalhouse.bclip.model.Preferencia;
import io.reactivex.Flowable;


@Dao
public interface PreferenciaDao {

    @Query("SELECT * FROM preferenciaTable")
    Flowable<List<Preferencia>> getAll();

}
