package br.com.digitalhouse.bclip.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.bclip.model.NoticiaFromApi;
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

    public void atualizarNoticiasFromApi (String search) {
        disposable.add(
                noticiasRepository.getNoticiaFromApiList(search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(noticiasFromApiList -> noticiaFromApiLiveData.setValue(noticiasFromApiList))
        );
    }
}
