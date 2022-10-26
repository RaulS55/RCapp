package or.example.rdesmarket_v10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CancelarTurno extends AppCompatActivity {
    TextView mensaje;
    Button aceptar,salir,atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_turno);

        mensaje = (TextView) findViewById(R.id.lb_mensaje2_cancelar);
        aceptar = (Button) findViewById(R.id.bt_aceptar_cancelar);
        salir = (Button) findViewById(R.id.bt_salir_cancelar);
        atras = (Button) findViewById(R.id.bt_atras_cancelar);
    }

    public void cancelar(View view){
        Intent pas = new Intent(this,MainActivity.class);
        startActivity(pas);
    }
    public void consulta(View view){
        Intent pas = new Intent(this,Consulta.class);
        startActivity(pas);
    }

    public void aceptar(View view){
        mensaje.setVisibility(View.VISIBLE);
        salir.setVisibility(View.VISIBLE);
        aceptar.setEnabled(false);
        atras.setEnabled(false);
    }
}