package or.example.rdesmarket_v10;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import or.example.rdesmarket_v10.utilidades.Utilidades;
import or.example.rdesmarket_v10.utilidades.UtilidadesFecha;

public class ListAdapterFechas extends RecyclerView.Adapter<ListAdapterFechas.ViewHolderLista> implements View.OnClickListener {
    ArrayList<String> lista_horas,lista_dias;
    private View.OnClickListener listener;
    int posicionMaarcada = 0;

    public ListAdapterFechas(ArrayList<String> lista_horas, ArrayList<String> lista_dias) {
        this.lista_horas = lista_horas;
        this.lista_dias = lista_dias;
    }

    @NonNull
    @Override
    public ViewHolderLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_fechas,null,false);
        view.setOnClickListener(this);
        return new ViewHolderLista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLista holder, int position) {
        holder.asignarHoras(lista_horas.get(position));
        holder.dia.setText(lista_dias.get(position));
        final int pos = position;
        holder.hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posicionMaarcada=pos;
                notifyDataSetChanged();
                UtilidadesFecha.seleccion=lista_horas.get(pos);
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posicionMaarcada=pos;
                notifyDataSetChanged();
                UtilidadesFecha.seleccion=lista_horas.get(pos);
            }
        });

        if (posicionMaarcada==position){
            //holder.hora.setBackgroundColor(Color.parseColor("#FF1DD6D0"));//FF27FD01
            holder.layout.setBackgroundColor(Color.parseColor("#FF1DD6D0"));//FF27FD01
        }else {
            //holder.hora.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            holder.layout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return lista_horas.size();
    } //TAMAÑO DE LA LISTA


    //METODOS DEL ONCLICK-------------------------
    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }
    //--------------------------------------------
    public class ViewHolderLista extends RecyclerView.ViewHolder {
        TextView hora,dia;
        LinearLayout layout;
        public ViewHolderLista(@NonNull View itemView) {
            super(itemView);
            dia = (TextView) itemView.findViewById(R.id.tv_list_dias);
            hora = (TextView) itemView.findViewById(R.id.tv_list_fechas);
            layout = (LinearLayout) itemView.findViewById(R.id.layautFecha);

        }

        public void asignarHoras(String s) {
            hora.setText(s);
        }
        public void asignarDias(String s) {
            dia.setText(s);
        }
    }


}
