package br.com.digitalhouse.bclip.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import br.com.digitalhouse.bclip.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {


    public BuscarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_noticia_preferencias, container, false);
    }

}
