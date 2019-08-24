package br.com.digitalhouse.bclip.modules.NoticiasPreferencias.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.bclip.model.NoticiaFromApi;
import br.com.digitalhouse.bclip.model.Preferencia;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import br.com.digitalhouse.bclip.repository.NoticiasRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NoticiaPreferenciasViewModel extends AndroidViewModel {

    private MutableLiveData<List<NoticiaFromApi>> noticiaFromApiLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private NoticiasRepository noticiasRepository = new NoticiasRepository();

    public NoticiaPreferenciasViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<NoticiaFromApi>> getNoticiaFromApiLiveData() {
        return noticiaFromApiLiveData;
    }

    public void atualizarNoticiasPreferenciaFromApi (List<Preferencia> preferenciaList) {
        String search = new String();

        for (int i = 0; i < preferenciaList.size(); i++){
            search += preferenciaList.get(i).getPreferencia();
            if(i < preferenciaList.size() - 1){
                search += " , ";
            }
        }


        disposable.add(
                noticiasRepository.getNoticiaFromApiList(search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(noticiasFromApiList -> noticiaFromApiLiveData.setValue(noticiasFromApiList))
        );
    }
}
