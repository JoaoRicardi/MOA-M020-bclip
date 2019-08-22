package br.com.digitalhouse.bclip.modules.Login.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import br.com.digitalhouse.bclip.repository.FirebaseRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class LoginViewModel extends AndroidViewModel {



    MutableLiveData<Boolean> autenticadoLiveData = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();
    FirebaseRepository repository = new FirebaseRepository();


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> getAutenticadoLiveData() {
        return autenticadoLiveData;
    }

    public void autenticarUsuario(String email, String senha){
        disposable.add(
                repository.autenticar(email, senha)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe( () -> autenticadoLiveData.setValue(true),
                        throwable -> autenticadoLiveData.setValue(false))
        );
    }
}
