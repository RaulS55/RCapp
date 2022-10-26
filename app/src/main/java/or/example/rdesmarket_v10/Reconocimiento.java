package or.example.rdesmarket_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Reconocimiento extends AppCompatActivity{

    private Spinner spinner1;
    //private TextView resultado;
    private EditText dni,nombre,apellido,direccion,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconocimiento);

        dni = (EditText) findViewById(R.id.et_Dni2);
        nombre = (EditText) findViewById(R.id.ed_Nombre_R);
        apellido = (EditText) findViewById(R.id.et_Apellido_R);
        direccion = (EditText) findViewById(R.id.et_Dir2);
        telefono = (EditText) findViewById(R.id.et_Telefono2);

        llenarSpiner1();
        seleccionSpinner1();

    }


    //**********************************METODOS CREADOS**************************************************
    public void llenarSpiner1(){
        spinner1 = (Spinner) findViewById(R.id.spinner_reg_cercano);
        String[] opciones_regCercano = {"seleccionar","San Salvador-Capital 1-B° San Pedrito","San Salvador-Capital 2-B° Cuyaya", "San Salvador-Casa Central",
                "San Salvador-Alto Comedero", "San Pedro", "Palpalá", "Perico", "El Carmen", "Barrio Ledesma", "Tilcara", "Humahuaca", "La Quiaca", "Monterrico",
                "Abra Pampa", "Hospital Materno Infantil-Héctor Quintana", "Libertador", "Fraile Pintado", "Palma Sola", "Yuto", "Puesto Viejo", "Santa Clara"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,opciones_regCercano);
        spinner1.setAdapter(adapter);

    }

    public  void seleccionSpinner1(){
        //Actualiza segun la seleccion en el Spinner
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selecDepartamento = spinner1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    public void cambiarAFecha_R(View view){

        String valorDni = dni.getText().toString();
        String valorNom = nombre.getText().toString();
        String valorApe = apellido.getText().toString();
        String valorTel = telefono.getText().toString();
        String valordir = direccion.getText().toString();

        if (!valorDni.isEmpty() && !valorNom.isEmpty() && !valorApe.isEmpty() && !valorTel.isEmpty() && !valordir.isEmpty() &&
                spinner1.getSelectedItem().toString()!="seleccionar"){

            //Toast.makeText(this,"PASA",Toast.LENGTH_SHORT).show();

            TurnoObj turno = new TurnoObj("Reconocimiento",valorNom,valorApe,valorDni,valordir,valorTel,spinner1.getSelectedItem().toString());
            Intent pasar = new Intent(this, Fecha.class);
            pasar.putExtra("turno",turno);
            startActivity(pasar);

        }else {
            Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show();
        }

    }


}
