package br.com.digitalhouse.bclip.repository;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import br.com.digitalhouse.bclip.database.AppDatabase;
import br.com.digitalhouse.bclip.database.AppDatabasePreferencia;
import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PreferenciaRepository {

   public Flowable<List<Preferencia>> getListaPreferencia(Context context){
       AppDatabasePreferencia db = Room.databaseBuilder(context, AppDatabasePreferencia.class, AppDatabasePreferencia.DATABASE_NAME).build();
       return db.preferenciasDao().getAll();
   }

   public Completable inserirPreferencia(Context context, Preferencia preferencia){
       AppDatabasePreferencia db = Room.databaseBuilder(context, AppDatabasePreferencia.class, AppDatabasePreferencia.DATABASE_NAME).build();
       return Completable.fromAction(()->db.preferenciasDao().insert(preferencia))
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.newThread());
   }
//
//    public Completable deletarPreferencia(Context context, Preferencia preferencia){
//        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
//        return Completable.fromAction(()->db.preferenciasDao().delete(preferencia))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread());
//    }
//
//    public Completable updatePreferencia(Context context, Preferencia preferencia){
//        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
//        return Completable.fromAction(()->db.preferenciasDao().update(preferencia))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread());
//    }

}
