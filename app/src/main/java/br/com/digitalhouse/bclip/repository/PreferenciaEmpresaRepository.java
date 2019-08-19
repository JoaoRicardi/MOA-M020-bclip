package br.com.digitalhouse.bclip.repository;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import br.com.digitalhouse.bclip.database.AppDatabase;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PreferenciaEmpresaRepository {

   public Flowable<List<PreferenciaEmpresas>> getListaPreferenciaEmpresas(Context context){
       AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
       return db.preferenciaEmpresasDao()
               .getAll();
   }

   public Completable inserirPreferenciaEmpresa(Context context, PreferenciaEmpresas preferenciaEmpresas){
       AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
       return Completable.fromAction(()->db.preferenciaEmpresasDao().insert(preferenciaEmpresas))
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.newThread());
   }

    public Completable deletarPreferenciaEmpresa(Context context, PreferenciaEmpresas preferenciaEmpresas){
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        return Completable.fromAction(()->db.preferenciaEmpresasDao().delete(preferenciaEmpresas))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Completable updatePreferenciaEmpresa(Context context, PreferenciaEmpresas preferenciaEmpresas){
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        return Completable.fromAction(()->db.preferenciaEmpresasDao().update(preferenciaEmpresas))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

}
