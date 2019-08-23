package br.com.digitalhouse.bclip.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.adapters.FavoritasAdapter;
import br.com.digitalhouse.bclip.adapters.NoticiaAdapter;
import br.com.digitalhouse.bclip.interfaces.NoticiaListener;
import br.com.digitalhouse.bclip.model.Noticia;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;
import br.com.digitalhouse.bclip.model.Source;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritasFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritasAdapter favoritasAdapter;
    private FirebaseFirestore firebaseDb = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private static final String TAG = "FavoritaFragment";



    public FavoritasFragment() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritas, container, false);

        recyclerView = view.findViewById(R.id.favoritas_recycler_view_id);

        ArrayList<NoticiaFromApi> noticiaFavoritaList = new ArrayList<>();

        favoritasAdapter = new FavoritasAdapter(noticiaFavoritaList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setAdapter(favoritasAdapter);
        recyclerView.setLayoutManager(layoutManager);

        buscarDadosFirebase();




        return view;
    }


    private void buscarDadosFirebase() {
        firebaseDb.collection("usuario")
                .document(firebaseUser.getUid())
                .collection("noticias favoritas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<NoticiaFromApi> noticiaFromApiList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + "=>" + document.getData());

                                NoticiaFromApi noticiaFromApi = new NoticiaFromApi();

                                noticiaFromApi.setTitle((String) document.getData().get("title"));
                                Source source = new Source();
                                source.setName((String) document.getData().get("source"));
                                noticiaFromApi.setSource(source);
                                noticiaFromApi.setDescription((String) document.getData().get("description"));
                                noticiaFromApi.setUrlToImage((String) document.getData().get("urlToImage"));
                                noticiaFromApi.setUrl((String) document.getData().get("url"));

                                noticiaFromApiList.add(noticiaFromApi);
                                favoritasAdapter.atualizarFavoritas(noticiaFromApiList);

                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }


                });

    }




}