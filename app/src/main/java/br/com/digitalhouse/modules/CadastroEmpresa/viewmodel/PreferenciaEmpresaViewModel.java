package br.com.digitalhouse.modules.CadastroEmpresa.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.bclip.adapters.PreferenciasEmpresasAdapter;
import br.com.digitalhouse.bclip.database.AppDatabase;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import br.com.digitalhouse.bclip.repository.PreferenciaEmpresaRepository;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PreferenciaEmpresaViewModel extends AndroidViewModel {

    private  MutableLiveData<List<PreferenciaEmpresas>> listPreferenciasEmpresaLiveData = new MutableLiveData<>();

    private MutableLiveData<PreferenciaEmpresas> preferenciaEmpresasMutableLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();
//    private AppDatabase db;
    private PreferenciaEmpresaRepository repository= new PreferenciaEmpresaRepository();


//    private PreferenciasEmpresasAdapter.OnPreferenciaEmpresaListener onPreferenciaEmpresaListener;





    public PreferenciaEmpresaViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<List<PreferenciaEmpresas>> getListPreferenciasEmpresa() {
        return listPreferenciasEmpresaLiveData;
    }
//    public MutableLiveData<PreferenciaEmpresas> getPreferenciaEmpresasLiveData(){
//        return preferenciaEmpresasMutableLiveData;
//    }

    //List<PreferenciaEmpresas> preferenciaEmpresasList = db.preferenciaEmpresasDao().getAll();



    public void inserirPreferenciasEmpresa(PreferenciaEmpresas preferenciaEmpresas){
        disposable.add(
                repository.inserirPreferenciaEmpresa(getApplication(), preferenciaEmpresas)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(()->preferenciaEmpresasMutableLiveData.setValue(preferenciaEmpresas))
        );
    }

    public void deletarPreferenciaEmpresa (PreferenciaEmpresas preferenciaEmpresas){
        disposable.add(
                repository.deletarPreferenciaEmpresa(getApplication(), preferenciaEmpresas)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(()->preferenciaEmpresasMutableLiveData.setValue(preferenciaEmpresas))
        );
    }
/// null pointer corrigir
    public void updatePreferenciaEmpresa(PreferenciaEmpresas preferenciaEmpresas) {
        disposable.add(
                repository.updatePreferenciaEmpresa(getApplication(), preferenciaEmpresas)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(()->preferenciaEmpresasMutableLiveData.setValue(preferenciaEmpresas))
        );

    }

    public void getListaPreferenciaEmpresas() {
        disposable.add(
                repository.getListaPreferenciaEmpresas(getApplication())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(lista->listPreferenciasEmpresaLiveData.setValue(lista))
        );

    }

}
