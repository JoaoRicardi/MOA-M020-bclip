package br.com.digitalhouse.bclip.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.interfaces.PreferenciaListener;
import br.com.digitalhouse.bclip.model.Preferencia;

public class PreferenciasAdapter extends RecyclerView.Adapter<PreferenciasAdapter.ViewHolder> implements PreferenciaListener {

    private List<Preferencia> listaPreferencias;
    private PreferenciaListener preferenciaListener;

    public PreferenciasAdapter(List<Preferencia> listaPreferencias, PreferenciaListener preferenciaListener) {
        this.listaPreferencias = listaPreferencias;
        this.preferenciaListener = preferenciaListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celula_preferencia, parent, false);
        return new ViewHolder(view, preferenciaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Preferencia preferencia = listaPreferencias.get(i);
        holder.setupPreferencias(preferencia);

    }

    @Override
    public int getItemCount() {
        return listaPreferencias.size();
    }

    @Override
    public void preferenciaClicado(int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView preferenciaTextView;
        private Preferencia preferencia;
        PreferenciaListener preferenciaListener;

        public ViewHolder(@NonNull View itemView, PreferenciaListener preferenciaListener) {
            super(itemView);

            preferenciaTextView = itemView.findViewById(R.id.preferencia_celula_id);
            this.preferenciaListener = preferenciaListener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferencia.setAtivado(!preferencia.getAtivado());
                    setupCelula(view, preferencia);
                }
            });

        }

        private void setupCelula(View view, Preferencia preferencia) {
            if(preferencia.getAtivado()){
                Drawable icon = view.getContext().getResources().getDrawable((R.drawable.ic_check_black_24dp));
                preferenciaTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);

                int colorAtivado = view.getContext().getResources().getColor(R.color.colorAccentDark);
                preferenciaTextView.setBackgroundColor(colorAtivado);
            } else{
                Drawable icon = view.getContext().getResources().getDrawable(R.drawable.ic_add_black_24dp);
                preferenciaTextView.setCompoundDrawablesWithIntrinsicBounds(null,null,icon,null);

                int colorDesativado = view.getContext().getResources().getColor(R.color.colorAccent);
                preferenciaTextView.setBackgroundColor(colorDesativado);
            }
        }

        public void setupPreferencias(Preferencia preferencia){
            preferenciaTextView.setText(preferencia.getPreferencia());
            this.preferencia = preferencia;

            setupCelula(preferenciaTextView, preferencia);
        }

    }
}
