package or.example.rdesmarket_v10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Consulta extends AppCompatActivity {
Button cancelar,modificar;
TextView nombre,apellido,tramite,oficina,fecha,hora;
    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        cancelar = (Button) findViewById(R.id.bt_cancelar);
        modificar = (Button) findViewById(R.id.bt_modificar);
        nombre = (TextView) findViewById(R.id.lb_nombre_consul);
        apellido = (TextView) findViewById(R.id.lb_apellido_consul);
        tramite = (TextView) findViewById(R.id.lb_tipo_consul);
        oficina = (TextView) findViewById(R.id.lb_Oficina_consul);
        fecha = (TextView) findViewById(R.id.lb_fecha_consul);
        hora = (TextView) findViewById(R.id.lb_hora_consul);

        llenarSpiner1();
    }


    public void llenarSpiner1(){
        spinner1 = (Spinner) findViewById(R.id.spinner_consulta);
        String[] opciones = {"Nacimiento","DNI/Pasaporte","Reconocimiento"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,opciones);
        spinner1.setAdapter(adapter);
    }

    //Pasar a la interfaz solicitud de codigo de operacion
    public void cambiarSolCodOperacion(View view){
        Bundle extras = new Bundle();

        switch(view.getId()){
            case R.id.bt_modificar:
                extras.putString("boton", "modificar");
                break;

            case R.id.bt_cancelar:
                extras.putString("boton", "cancelar");
                break;
        }
        Intent pasar = new Intent(this, SolCodOperacion.class);
        pasar.putExtras(extras);
        startActivity(pasar);
    }

    //Mostrar datos encontrados
    public void buscar(View view){
        cancelar.setVisibility(View.VISIBLE);
        modificar.setVisibility(View.VISIBLE);

        //nombre.setText("Nombre: ");
    }
}
