package br.com.digitalhouse.bclip.modules.Noticias.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.stream.Collectors;

import br.com.digitalhouse.bclip.activities.HomeActivity;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;
import br.com.digitalhouse.bclip.repository.NoticiasRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NoticiaViewModel extends AndroidViewModel {

    private MutableLiveData<List<NoticiaFromApi>> noticiaFromApiLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private NoticiasRepository noticiasRepository = new NoticiasRepository();

    public NoticiaViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<NoticiaFromApi>> getNoticiaFromApiLiveData() {
        return noticiaFromApiLiveData;
    }

    public void atualizarNoticiasFromApi (List<PreferenciaEmpresas> preferenciaEmpresasList) {
        String search = new String();

        for (int i = 0; i < preferenciaEmpresasList.size(); i++){
            String preferenciaEmpresa = preferenciaEmpresasList.get(i).getPreferenciaEmpresa();
            if(!preferenciaEmpresa.isEmpty()){
                search += preferenciaEmpresa;
                if(i < preferenciaEmpresasList.size() - 1 ){
                    search += " OR ";
                }
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
