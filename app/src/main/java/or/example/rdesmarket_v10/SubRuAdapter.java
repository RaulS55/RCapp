package or.example.rdesmarket_v10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubRuAdapter extends RecyclerView.Adapter<SubRuAdapter.ViewHolderSub> implements View.OnClickListener {
    private View.OnClickListener listener;
    ArrayList<SubRuOBj> lista_sub;

    public SubRuAdapter(ArrayList<SubRuOBj> lista_sub) {
        this.lista_sub = lista_sub;
    }

    @NonNull
    @Override
    public ViewHolderSub onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subrubro_row,null,false);
        view.setOnClickListener(this);
        return new ViewHolderSub(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSub holder, int position) {
        holder.etiSubRubro.setText(lista_sub.get(position).getSubRubro());
    }

    @Override
    public int getItemCount() {
        return lista_sub.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderSub extends RecyclerView.ViewHolder {
        TextView etiSubRubro;
        public ViewHolderSub(@NonNull View itemView) {
            super(itemView);
            etiSubRubro = itemView.findViewById(R.id.tv_subRubro);
        }
    }
}
