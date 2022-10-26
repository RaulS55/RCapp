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

public class ListAdapterHoras extends RecyclerView.Adapter<ListAdapterHoras.ViewHolderLista> implements View.OnClickListener {
    ArrayList<String> lista_horas;
    private View.OnClickListener listener;
    int posicionMaarcada = 0;

    public ListAdapterHoras(ArrayList<String> lista_horas) {
        this.lista_horas = lista_horas;
    }

    @NonNull
    @Override
    public ViewHolderLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_horas,null,false);
        view.setOnClickListener(this);
        return new ViewHolderLista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLista holder, int position) {
        holder.asignarHoras(lista_horas.get(position));
        final int pos = position;
        holder.hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posicionMaarcada=pos;
                notifyDataSetChanged();
                Utilidades.seleccion=lista_horas.get(pos);
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posicionMaarcada=pos;
                notifyDataSetChanged();
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
        TextView hora;
        LinearLayout layout;
        public ViewHolderLista(@NonNull View itemView) {
            super(itemView);
            hora = (TextView) itemView.findViewById(R.id.tv_list_horas);
            layout = (LinearLayout) itemView.findViewById(R.id.layautHoras);

        }

        public void asignarHoras(String s) {
            hora.setText(s);
        }
    }
}
