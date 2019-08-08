package br.com.digitalhouse.modules.Preferencias.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.bclip.repository.NoticiasRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PreferenciasViewModel extends AndroidViewModel {

    private MutableLiveData<List<Preferencia>> listaPreferenciasLiveData = new MutableLiveData<List<Preferencia>>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private NoticiasRepository noticiasRepository = new NoticiasRepository();


    public PreferenciasViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<List<Preferencia>> getListaPreferenciasLiveData() {
        return listaPreferenciasLiveData;
    }

    public void atualizarLista(){
        disposable.add(
                noticiasRepository.getListaPreferencias(getApplication())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(listaComprasList -> {
                            listaPreferenciasLiveData.setValue(listaComprasList);
                        }, throwable -> throwable.printStackTrace())
        );
    }

    public void inserirListaPreferencias(ListaPreferencias listaPreferencias){
        disposable.add(
                noticiasRepository.inserirListaPreferencias(listaPreferencias, getApplication())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> atualizarLista())
        );
    }

}
