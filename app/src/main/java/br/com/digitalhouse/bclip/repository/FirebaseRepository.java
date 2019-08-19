package br.com.digitalhouse.bclip.repository;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.model.Usuario;
import br.com.digitalhouse.bclip.modules.CadastroUsuário.view.CadastroActivity;
import io.reactivex.Completable;
import io.reactivex.Observable;

public class FirebaseRepository {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private static final String TAG = "FirebaseRepository";

    public Completable autenticar(String email, String senha){
        return Completable.create(emitter -> {
            firebaseAuth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                emitter.onComplete();
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                emitter.onError(task.getException());
                            }

                        }
                    });

        });
    }

    public Completable cadastrarUsuario(String nome, String email, String senha, Activity activity){
        return Completable.create(emitter -> {

            firebaseAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Usuário cadastrado com sucesso!");
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nome)
                                        .build();

                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "Usuário atualizado");
                                                    emitter.onComplete();
                                                }
                                            }
                                        });

                            } else {
                                Log.w(TAG, "Falha no cadastro", task.getException());
                                emitter.onError(task.getException());
                            }
                        }
                    });
        });
    }

}
