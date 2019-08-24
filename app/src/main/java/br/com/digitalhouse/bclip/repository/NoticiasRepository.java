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
    private static final String API_KEY = "2af0470fc1404d868a96ee30adfeecbe";
    private static final String FORMAT = "json";
    private static final int PAGESIZE = 50;
    private static final String CATEGORY = "science";
    private static final String SORTBY = "relevancy";
    private static final String COUNTRY = "br";

    public Observable<List<NoticiaFromApi>> getNoticiaFromApiList(String search) {
        return retrofitService.getNoticiasApi()
                .getNoticiasFromApi(API_KEY, FORMAT, search, PAGESIZE, SORTBY)
                .map(noticiaFromApiResponse -> noticiaFromApiResponse.getArticles());
    }

// noticias preferencias
    public Observable<List<NoticiaFromApi>> getNoticiaFromApiListPreferencias(String search) {
        return retrofitService.getNoticiasApiPreferencias()
                .getNoticiasFromApiPreferencias(API_KEY, FORMAT,CATEGORY, search, PAGESIZE, COUNTRY)
                .map(noticiaFromApiResponse -> noticiaFromApiResponse.getArticles());
    }


}


