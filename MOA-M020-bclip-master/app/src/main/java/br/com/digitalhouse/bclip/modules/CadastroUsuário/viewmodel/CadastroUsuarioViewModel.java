package br.com.digitalhouse.bclip.modules.CadastroUsuário.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.bclip.model.Usuario;
import br.com.digitalhouse.bclip.repository.FirebaseRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CadastroUsuarioViewModel extends AndroidViewModel {

    MutableLiveData<Boolean> cadastradoLiveData = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();
    FirebaseRepository repository = new FirebaseRepository();


    public CadastroUsuarioViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> getCadastradoLiveData() {
        return cadastradoLiveData;
    }

    public void cadastrarUsuario(String nome, String email, String senha, Activity activity){
        disposable.add(
                repository.cadastrarUsuario(nome, email, senha, activity)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe( () -> cadastradoLiveData.setValue(true),
                                throwable -> {
                            throwable.printStackTrace();
                            cadastradoLiveData.setValue(false);
                                })
        );
    }
}

