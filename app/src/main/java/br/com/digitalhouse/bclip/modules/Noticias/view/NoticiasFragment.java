package br.com.digitalhouse.bclip.modules.Noticias.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.activities.DetalheNoticia;
import br.com.digitalhouse.bclip.adapters.NoticiaAdapter;
import br.com.digitalhouse.bclip.database.AppDatabase;
import br.com.digitalhouse.bclip.interfaces.NoticiaListener;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;
import br.com.digitalhouse.bclip.modules.Noticias.viewmodel.NoticiaViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasFragment extends Fragment implements NoticiaListener {


    private AppDatabase db;
    private FirebaseFirestore firebaseDb = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser;
    private NoticiaAdapter noticiaAdapter;

    private static final String TAG = "NoticiaFragment";


    public NoticiasFragment() {
        // Required empty public constructor
    }

    private NoticiaListener noticiaListerner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        ArrayList<NoticiaFromApi> noticiaFromApiArrayList = new ArrayList<>();

        NoticiaAdapter noticiaAdapter = new NoticiaAdapter(noticiaFromApiArrayList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.noticias_recycler_id);

        recyclerView.setAdapter(noticiaAdapter);
        recyclerView.setLayoutManager(layoutManager);

        NoticiaViewModel noticiaViewModel = ViewModelProviders.of(getActivity()).get(NoticiaViewModel.class);

        db = Room.databaseBuilder(getContext(),
                AppDatabase.class, AppDatabase.DATABASE_NAME).build();

        db.preferenciaEmpresasDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(preferenciaEmpresas -> {
                    noticiaViewModel.atualizarNoticiasFromApi(preferenciaEmpresas);
                });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            String email = firebaseUser.getEmail();
            String nome = firebaseUser.getDisplayName();
        }


        noticiaViewModel.getNoticiaFromApiLiveData()
                .observe(getActivity(), noticiaFromApiList -> noticiaAdapter.atualizarNoticias(noticiaFromApiList));


        return view;


    }

    @Override
    public void onNoticiaClicado(NoticiaFromApi noticia) {
        Intent intent = new Intent(getContext(), DetalheNoticia.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("NOTICIA", noticia);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void salvarFavorito(NoticiaFromApi noticia, ImageView salvarNoticiaButton) {
        Map<String, Object> noticiaDb = new HashMap<>();
        noticiaDb.put("title", noticia.getTitle());
        noticiaDb.put("source", noticia.getSource().getName());
        noticiaDb.put("description", noticia.getDescription());
        noticiaDb.put("urlToImage", noticia.getUrlToImage());
        noticiaDb.put("url", noticia.getUrl());
        noticiaDb.put("publishedAt", noticia.getPublishedAt());

        firebaseDb.collection("usuario")
                .document(firebaseUser.getUid())
                .collection("noticias favoritas")
                .document(noticia.getTitle())
                .set(noticiaDb)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        salvarNoticiaButton.setBackgroundResource(R.drawable.ic_favoritas_on);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void compartilharNoticia(NoticiaFromApi noticia) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, noticia.getTitle());
        intent.putExtra(Intent.EXTRA_SUBJECT, noticia.getUrl());
        startActivity(Intent.createChooser(intent, "Compartilhar"));

    }

    @Override
    public void setupFavoritoButton(NoticiaFromApi noticia, ImageView salvarNoticiaButton) {
        firebaseDb.collection("usuario")
                .document(firebaseUser.getUid())
                .collection("noticias favoritas")
                .whereEqualTo("title", noticia.getTitle())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            if (task.getResult().size() > 0) {
                                salvarNoticiaButton.setBackgroundResource(R.drawable.ic_favoritas_on);
                                noticia.setFavorito(true);
                            } else {
                                salvarNoticiaButton.setBackgroundResource(R.drawable.ic_favoritas_off);
                                noticia.setFavorito(false);
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    @Override
    public void deletarFavorito(NoticiaFromApi noticia) {
        firebaseDb.collection("usuario")
                .document(firebaseUser.getUid())
                .collection("noticias favoritas")
                .document(noticia.getTitle())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });

                    }



    }


