package or.example.rdesmarket_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class SolCodOperacion extends AppCompatActivity {
    String bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod_operacion);

        String boton = getIntent().getExtras().getString("boton");
        bt = boton;

    }

    public void Modificar(View view){
        if (bt.equals("modificar") ) {
            Intent pas = new Intent(this, Fecha.class);//
            startActivity(pas);
        }else{
            Intent pasar = new Intent(this, CancelarTurno.class);//
            startActivity(pasar);
        }
    }
}
