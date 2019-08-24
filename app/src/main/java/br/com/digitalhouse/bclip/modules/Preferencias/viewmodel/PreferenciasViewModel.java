package br.com.digitalhouse.bclip.modules.Preferencias.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import br.com.digitalhouse.bclip.repository.NoticiasRepository;
import br.com.digitalhouse.bclip.repository.PreferenciaRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PreferenciasViewModel extends AndroidViewModel {


    private MutableLiveData<List<Preferencia>> listaPreferenciaLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private PreferenciaRepository repository= new PreferenciaRepository();


    public PreferenciasViewModel(@NonNull Application application) { super(application); }
    public MutableLiveData<List<Preferencia>> getListaPreferencia() {
        return listaPreferenciaLiveData;
    }

}