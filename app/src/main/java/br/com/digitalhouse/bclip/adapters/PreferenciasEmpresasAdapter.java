package br.com.digitalhouse.bclip.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.bclip.R;
import br.com.digitalhouse.bclip.database.AppDatabase;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;


public class PreferenciasEmpresasAdapter extends RecyclerView.Adapter<PreferenciasEmpresasAdapter.ViewHolder>  {


    private List<PreferenciaEmpresas> listaPreferenciaEmpresas = new ArrayList<>();

    private OnPreferenciaEmpresaListener onPreferenciaEmpresaListener;

    private AppDatabase db;


    public void atualizarPreferenciaEmpresa(List<PreferenciaEmpresas> listaPreferenciaEmpresas){
        this.listaPreferenciaEmpresas = listaPreferenciaEmpresas;
        notifyDataSetChanged();
    }

    public interface OnPreferenciaEmpresaListener {
        void onPreferenciaLongClick(PreferenciaEmpresas preferenciaEmpresas);
    }



    public PreferenciasEmpresasAdapter(OnPreferenciaEmpresaListener onPreferenciaEmpresaListener){
        this.onPreferenciaEmpresaListener = onPreferenciaEmpresaListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celula_preferencias_empresa, viewGroup, false);
        return new ViewHolder(view, onPreferenciaEmpresaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final PreferenciaEmpresas preferenciaEmpresas = listaPreferenciaEmpresas.get(i);
        viewHolder.setupPreferenciaEmpresa(preferenciaEmpresas);

    }



    @Override
    public int getItemCount() {
        return listaPreferenciaEmpresas.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView btnPreferenciaEmpresas;
        OnPreferenciaEmpresaListener onPreferenciaEmpresaListener;
        private PreferenciaEmpresas preferenciaEmpresas;

        public ViewHolder(@NonNull View itemView, OnPreferenciaEmpresaListener onPreferenciaEmpresaListener) {
            super(itemView);





            btnPreferenciaEmpresas = itemView.findViewById(R.id.preferecia_empresas_button);
            this.onPreferenciaEmpresaListener = onPreferenciaEmpresaListener;


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    preferenciaEmpresas.setAtivado(!preferenciaEmpresas.getAtivado());
                    bind(v, preferenciaEmpresas);
                }
            });
        }

        private void bind(View v, PreferenciaEmpresas preferenciaEmpresas) {


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    onPreferenciaEmpresaListener.onPreferenciaLongClick(preferenciaEmpresas);

                    return true;
                }
            });


            if (preferenciaEmpresas.getAtivado()) {

                Drawable icon = v.getContext().getResources().getDrawable(R.drawable.ic_check_black_24dp);
                btnPreferenciaEmpresas.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);

                btnPreferenciaEmpresas.setText(preferenciaEmpresas.getPreferenciaEmpresa());
                Toast.makeText(v.getContext(), " Preferencia: " + preferenciaEmpresas.getPreferenciaEmpresa()+ " Ativada", Toast.LENGTH_SHORT).show();

                int colorAtivado = v.getContext().getResources().getColor(R.color.verde);
                btnPreferenciaEmpresas.setBackgroundColor(colorAtivado);


            } else {

                Drawable icon = v.getContext().getResources().getDrawable(R.drawable.ic_add_black_24dp);
                btnPreferenciaEmpresas.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);

                btnPreferenciaEmpresas.setText(preferenciaEmpresas.getPreferenciaEmpresa());
                int colorDesativado = v.getContext().getResources().getColor(R.color.Selecionado);
                btnPreferenciaEmpresas.setBackgroundColor(colorDesativado);

                Toast.makeText(v.getContext(), " Preferencia " + preferenciaEmpresas.getPreferenciaEmpresa()+ " Desativada!", Toast.LENGTH_SHORT).show();
            }
        }


        public void setupPreferenciaEmpresa(PreferenciaEmpresas preferenciaEmpresas){
            btnPreferenciaEmpresas.setText(String.valueOf(preferenciaEmpresas.getPreferenciaEmpresa()));
            this.preferenciaEmpresas = preferenciaEmpresas;

            bind(btnPreferenciaEmpresas, preferenciaEmpresas);
        }


//        public void deletarPreferenciaEmpresa(PreferenciaEmpresas preferenciaEmpresas){
//                Completable.fromAction(() -> db.preferenciaEmpresasDao().delete(preferenciaEmpresas))
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeOn(Schedulers.newThread());
//            }


        }







}
