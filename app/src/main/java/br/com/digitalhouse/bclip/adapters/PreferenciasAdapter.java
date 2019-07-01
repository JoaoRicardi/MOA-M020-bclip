package br.com.digitalhouse.bclip.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.model.Preferencia;


public class PreferenciasAdapter extends RecyclerView.Adapter<PreferenciasAdapter.ViewHolder>  {


    private List<Preferencia> listaPreferencias;

    private OnPreferenciaListener onPreferenciaListener;



    public interface OnPreferenciaListener{
        void onPreferenciaClick(int position);
    }




    public PreferenciasAdapter(List<Preferencia> listaPreferencias, OnPreferenciaListener onPreferenciaListener){
        this.listaPreferencias = listaPreferencias;
        this.onPreferenciaListener = onPreferenciaListener;


    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celula_preferencias, viewGroup, false);
        return new ViewHolder(view, onPreferenciaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Preferencia preferencia = listaPreferencias.get(i);
        viewHolder.setupPreferencias(preferencia);

    }



    @Override
    public int getItemCount() {
        return listaPreferencias.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView btnPreferencia;
        OnPreferenciaListener onPreferenciaListener;
        private Preferencia preferencia;

        public ViewHolder(@NonNull View itemView, OnPreferenciaListener onPreferenciaListener) {
            super(itemView);

            btnPreferencia = itemView.findViewById(R.id.preferecia_button);
            this.onPreferenciaListener = onPreferenciaListener;

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    preferencia.setAtivado(!preferencia.getAtivado());
                    setupCelula(v, preferencia);
                }
            });
        }

        private void setupCelula(View v, Preferencia preferencia) {
            if (preferencia.getAtivado()) {
                Drawable icon = v.getContext().getResources().getDrawable(R.drawable.ic_check_black_24dp);
                btnPreferencia.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
            } else {
                Drawable icon = v.getContext().getResources().getDrawable(R.drawable.ic_add_black_24dp);
                btnPreferencia.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
            }
        }


        public void setupPreferencias(Preferencia preferencia){
            btnPreferencia.setText(preferencia.getPreferencia());
            this.preferencia = preferencia;

            setupCelula(btnPreferencia, preferencia);
        }


    }



}
