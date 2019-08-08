package br.com.digitalhouse.bclip.repository;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import br.com.digitalhouse.bclip.database.AppDatabase;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;
import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.bclip.service.RetrofitService;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class NoticiasRepository {

    private RetrofitService retrofitService = new RetrofitService();
    private static final String API_KEY = "dc960b3b301f488d82eacb012a7668ca";
    private static final String FORMAT = "json";

    public Observable<List<NoticiaFromApi>> getNoticiaFromApiList (String search) {
        return retrofitService.getNoticiasApi()
                .getNoticiasFromApi(API_KEY,FORMAT, search)
                .map(noticiaFromApiResponse -> noticiaFromApiResponse.getArticles());
    }

    //public Flowable<List<Preferencia>> getListaPreferencias(Context context) {
    //    AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    //    return db.()
    //           .getAll();


    }

}
