package or.example.rdesmarket_v10;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<ProductoObj> todos_prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //Pasa a la interfaz DNI
    public void cambiarADni(View view){
            Intent pasar = new Intent(this, Dni.class);
            startActivity(pasar);
        }

    //Pasar a la interfaz Reconocimiento
    public void cambiarReconocimiento(View view){
        Intent pasar = new Intent(this, Reconocimiento.class);
        startActivity(pasar);
    }

        //Pasar a la interfaz nacimientos
        public void cambiarNacimiento(View view){
            Intent pasar = new Intent(this, Nacimientos.class);
            startActivity(pasar);
        }

    //Pasar a la interfaz consulta
        public void cambiarConsulta(View view){
            Intent pasar = new Intent(this, Consulta.class);
            pasar.putExtra("val","1");
            startActivity(pasar);
        }
}