package or.example.rdesmarket_v10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GreedAdapter extends RecyclerView.Adapter<GreedAdapter.ViewHolderRubro> implements View.OnClickListener {

    ArrayList<TurnoObj> list_rub;
    private View.OnClickListener listener;

    public GreedAdapter(ArrayList<TurnoObj> list_rub) {
        this.list_rub = list_rub;
    }


    @NonNull
    @Override
    public ViewHolderRubro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_rubro,null,false);
        view.setOnClickListener(this);
        return new ViewHolderRubro(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRubro holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list_rub.size();
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }

    }

    public class ViewHolderRubro extends RecyclerView.ViewHolder {

        TextView etiTitulo;
        ImageView imag;
        public ViewHolderRubro(@NonNull View itemView) {
            super(itemView);

            etiTitulo = (TextView) itemView.findViewById(R.id.tv_titulo);
            imag = (ImageView) itemView.findViewById(R.id.iv_rubro);
        }
    }
}
