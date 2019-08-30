package br.com.digitalhouse.bclip.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import io.reactivex.Completable;
import io.reactivex.Flowable;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface PreferenciaDao {

    @Query("SELECT * FROM preferenciaTable")
    Flowable<List<Preferencia>> getAll();

    @Insert(onConflict = REPLACE)
    void insert(Preferencia preferencia);

}
