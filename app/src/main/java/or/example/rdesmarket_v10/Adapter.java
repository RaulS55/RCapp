package or.example.rdesmarket_v10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.productosViewHolder> implements View.OnClickListener{

    ArrayList<ProductoObj> ListaProductosObj;
    private View.OnClickListener listener;


    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    class productosViewHolder extends RecyclerView.ViewHolder {
        TextView EtiNombre, EtiPrecio,EtiMarca;
        ImageView local;

        public productosViewHolder(@NonNull View itemView) {
            super(itemView);
            EtiNombre = itemView.findViewById(R.id.tv_nombre);
            EtiPrecio = itemView.findViewById(R.id.tv_precio);
            EtiMarca = itemView.findViewById(R.id.tv_marca);
            local = itemView.findViewById(R.id.iv_local);
        }

    }

    public Adapter(ArrayList<ProductoObj> listaProductosObj) {
        ListaProductosObj = listaProductosObj;
    }


    @NonNull
    @Override

    //!!!!!Puebla la vista de los elementos
    public productosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_row,parent,false);
        view.setOnClickListener(this);

        productosViewHolder viewHolder = new productosViewHolder(view);
        return viewHolder;
    }

    @Override

    //!!!!!!
    public void onBindViewHolder(@NonNull productosViewHolder holder, int position) {
        ProductoObj prod = ListaProductosObj.get(position);
        holder.EtiNombre.setText((String) prod.getNombre());
        holder.EtiMarca.setText((String) prod.getMarca());
        holder.EtiPrecio.setText((String) prod.getPrecioCom());
        holder.local.setImageResource((prod.getLocal()));
    }

    @Override

    //!!!! tamaño de la lista
    public int getItemCount() {
        return ListaProductosObj.size();
    }

}
