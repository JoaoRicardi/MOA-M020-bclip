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
import br.com.digitalhouse.bclip.interfaces.OnPreferenciaEmpresaListener;
import br.com.digitalhouse.bclip.model.PreferenciaEmpresas;


public class PreferenciasEmpresasAdapter extends RecyclerView.Adapter<PreferenciasEmpresasAdapter.ViewHolder> {


    private List<PreferenciaEmpresas> listaPreferenciaEmpresas = new ArrayList<>();
    private OnPreferenciaEmpresaListener onPreferenciaEmpresaListener;



    //ok
    public PreferenciasEmpresasAdapter(OnPreferenciaEmpresaListener onPreferenciaEmpresaListener) {
        this.listaPreferenciaEmpresas= new ArrayList<>(); //incluido
        this.onPreferenciaEmpresaListener = onPreferenciaEmpresaListener;
    }


    //ok
    public void atualizarPreferenciaEmpresa(List<PreferenciaEmpresas> preferenciaEmpresaList) {
        this.listaPreferenciaEmpresas = preferenciaEmpresaList;
        notifyDataSetChanged();//avisa que houve alteraçao na lista (rx)
    }


    //ok
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

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                onPreferenciaEmpresaListener.deletarEmpresa(preferenciaEmpresas);  // <<<<<----- deleta empresa

                return false;
            }
        });
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
                    if(preferenciaEmpresas.getAtivado()){
                        Toast.makeText(v.getContext(), " Preferencia: " + preferenciaEmpresas.getPreferenciaEmpresa()+ " Ativada", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(v.getContext(), " Preferencia " + preferenciaEmpresas.getPreferenciaEmpresa()+ " Desativada!", Toast.LENGTH_SHORT).show();
                    }
                    bind(v, preferenciaEmpresas);
                }
            });
        }

        private void bind(View v, PreferenciaEmpresas preferenciaEmpresas) {
            if (preferenciaEmpresas.getAtivado()) {

                Drawable icon = v.getContext().getResources().getDrawable(R.drawable.ic_check_black_24dp);
                btnPreferenciaEmpresas.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);

                btnPreferenciaEmpresas.setText(preferenciaEmpresas.getPreferenciaEmpresa());
//                Toast.makeText(v.getContext(), " Preferencia: " + preferenciaEmpresas.getPreferenciaEmpresa()+ " Ativada", Toast.LENGTH_SHORT).show();

                int colorAtivado = v.getContext().getResources().getColor(R.color.verde);
                btnPreferenciaEmpresas.setBackgroundColor(colorAtivado);



            } else {

                Drawable icon = v.getContext().getResources().getDrawable(R.drawable.ic_add_black_24dp);
                btnPreferenciaEmpresas.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);

                btnPreferenciaEmpresas.setText(preferenciaEmpresas.getPreferenciaEmpresa());
                int colorDesativado = v.getContext().getResources().getColor(R.color.Selecionado);
                btnPreferenciaEmpresas.setBackgroundColor(colorDesativado);

//                Toast.makeText(v.getContext(), " Preferencia " + preferenciaEmpresas.getPreferenciaEmpresa()+ " Desativada!", Toast.LENGTH_SHORT).show();
            }
        }


        public void setupPreferenciaEmpresa(PreferenciaEmpresas preferenciaEmpresas) {
            btnPreferenciaEmpresas.setText(String.valueOf(preferenciaEmpresas.getPreferenciaEmpresa()));
            this.preferenciaEmpresas = preferenciaEmpresas;

            bind(btnPreferenciaEmpresas, preferenciaEmpresas);
        }
    }

}
