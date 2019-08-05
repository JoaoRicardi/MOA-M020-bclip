package br.com.digitalhouse.bclip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.adapters.NoticiaAdapter;
import br.com.digitalhouse.bclip.database.AppDatabase;
import br.com.digitalhouse.bclip.interfaces.NoticiaListener;
import br.com.digitalhouse.bclip.model.NoticiaFromApi;
import br.com.digitalhouse.bclip.viewmodel.NoticiaViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasFragment extends Fragment implements NoticiaListener{


    private AppDatabase db;

    public NoticiasFragment() {
        // Required empty public constructor
    }

    private NoticiaListener noticiaListerner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

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
                   // TODO chamar a API com todos da lista


                    noticiaViewModel.atualizarNoticiasFromApi(preferenciaEmpresas.get(0).getPreferenciaEmpresa());

                });


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


//    private List<Noticia> getListaNoticia() {
//        List<Noticia> noticiaList = new ArrayList<>();
//
//        Noticia noticia1 = new Noticia();
//        noticia1.setTituloMateria("Inteligência Artificial e Geolocalização \nintegram nova tecnologia ...");
//        noticia1.setDescricaoMateria("A Startup se destacou no mercado nacional por ser \numa plataforma onde é possível encontrar as lojas \nde moveis planejados mais próximas ...");
//        noticia1.setFotoMateria(R.drawable.img_tech);
//        noticiaList.add(noticia1);
//
//        Noticia noticia2 = new Noticia();
//        noticia2.setTituloMateria("Grandes empresas propõem desafios reais para as startups mais atrativas do mercado");
//        noticia2.setDescricaoMateria("Para a multinacional Basf, o uso de moedas virtuais vai ajudar a agregar mais valor aos parceiros da empresa e atrair novos players para a cadeia da indústria química. ...");
//        noticia2.setFotoMateria(R.drawable.img_tech2);
//        noticiaList.add(noticia2);
//
//        Noticia noticia3 = new Noticia();
//        noticia3.setTituloMateria("Mutant lança projeto para conectar startups às principais empresas do Brasil");
//        noticia3.setDescricaoMateria("Inscrições poderão ser feitas até o dia 26 de julho e um comitê ficará responsável pela aprovação dos parceiros");
//        noticia3.setFotoMateria(R.drawable.img_tech3);
//        noticiaList.add(noticia3);
//
//        Noticia noticia4 = new Noticia();
//        noticia4.setTituloMateria("Startup brasileira de ‘carne de planta’ recebe investimento de US$ 8,5 mi");
//        noticia4.setDescricaoMateria("Fundo brasileiro Monashees, que alavancou 99, Loggi e Rappi, entra com capital na Fazenda Futuro, empresa que está há apenas três meses no mercado");
//        noticia4.setFotoMateria(R.drawable.img_tech4);
//        noticiaList.add(noticia4);
//
//        Noticia noticia5 = new Noticia();
//        noticia5.setTituloMateria("Google ajuda a transformar sua startup em um grande negócio");
//        noticia5.setDescricaoMateria("Inscrições para o programa de aceleração do Google estão abertas até 9 de agosto\n");
//        noticia5.setFotoMateria(R.drawable.img_tech5);
//        noticiaList.add(noticia5);
//
//        noticiaList.add(noticia1);
//        noticiaList.add(noticia3);
//        noticiaList.add(noticia4);
//        noticiaList.add(noticia5);
//        noticiaList.add(noticia1);
//        noticiaList.add(noticia2);
//        noticiaList.add(noticia4);
//        noticiaList.add(noticia1);
//
//
//
//
//
//
//
//
//
//
//        return noticiaList;
//    }


}