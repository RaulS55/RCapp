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

public class Dni extends AppCompatActivity {


    private Spinner spinner1,spinner2;
    //private TextView resultado;
    private EditText dni,nombre,apellido,direccion,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dni);

        dni = (EditText) findViewById(R.id.et_Dni2);
        nombre = (EditText) findViewById(R.id.ed_Nombre1);
        apellido = (EditText) findViewById(R.id.et_Apellido2);
        direccion = (EditText) findViewById(R.id.et_Dir2);
        telefono = (EditText) findViewById(R.id.et_Telefono2);

        //resultado = (TextView) findViewById(R.id.id_resultado);
        //String valorDni = dni.getText().toString();
        //String valorDni = dni.getText().toString();

        spinner2 = (Spinner) findViewById(R.id.spinner_oficina);
        spinner2.setEnabled(false);

        llenarSpiner1();
        seleccionSpinner1(); // Permite el cambio de oficina al cambiar de valor el spinner
    }


    //**********************************METODOS CREADOS**************************************************

    public  void seleccionSpinner1(){
        //Actualiza segun la seleccion en el Spinner
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerDepartamento();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void seleccionSpinner2(){
        //Actualiza segun la seleccion en el Spinner
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selecOf = spinner2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }


    public void spinnerOficina(){
        String selecOficina = spinner2.getSelectedItem().toString();

    }




    //Metodo del Spinner Lugar de Nacimiento
    public void spinnerDepartamento(){
        String selecDepartamento = spinner1.getSelectedItem().toString();
        spinner2.setEnabled(false);
        String[] opcion;
        //llenarSpiner2(opcion);
        //seleccionSpinner2();

        switch (selecDepartamento){
            case "San Salvador":
                opcion = new String[]{"seleccionar", "Alto Comedero", "CDR en Anses", "CDR Nueva Terminal", "Capital 1 (Barrio San Pedrito)",
                        "Capital 2 (Barrio Cuyaya", "Central (Sólo DNI cero años", "Lozano"};
                llenarSpiner2(opcion);
                seleccionSpinner2();
                break;


            case "San Pedro":
                opcion = new String[]{"San Pedro"};
                llenarSpiner2(opcion);
                seleccionSpinner2();
                break;

            case "El Carmen":
                opcion = new String[]{"seleccionar", "El Carmen", "Monterrico", "Perico", "Perico-Rentas-Muni", "Puesto Viejo"};
                llenarSpiner2(opcion);
                seleccionSpinner2();
                break;

            case "Ledesma":
                opcion = new String[]{"seleccionar", "Fraile Pintado", "Lib. Gral. San Martín", "Yuto"};
                llenarSpiner2(opcion);
                seleccionSpinner2();
                break;

            case "La Quiaca":
                opcion = new String[]{"La Quiaca"};
                llenarSpiner2(opcion);
                seleccionSpinner2();

            case "Cochinoca":
                opcion = new String[]{"Abra Pampa"};
                llenarSpiner2(opcion);
                seleccionSpinner2();
                break;

            case "Palpalá":
                opcion = new String[]{"Palpalá"};
                llenarSpiner2(opcion);
                seleccionSpinner2();
                break;
            case "Tilcara":
                opcion = new String[]{"Tilcara"};
                llenarSpiner2(opcion);
                seleccionSpinner2();
                break;
            case "Santa Bárbara":
                opcion = new String[]{"seleccionar", "Palma Sola", "Santa Clara"};
                llenarSpiner2(opcion);
                seleccionSpinner2();
                break;
            case "Humahuaca":
                opcion = new String[]{"Humahuaca"};
                llenarSpiner2(opcion);
                seleccionSpinner2();
                break;

            default: llenarSpiner2(new String[]{"San Salvador"});
                seleccionSpinner2();;
        }

    }


    public void llenarSpiner1(){
        spinner1 = (Spinner) findViewById(R.id.spinner_reg_cercano);
        String[] opciones_departamento = {"seleccionar","San Salvador","San Pedro", "El Carmen",
                "Ledesma", "La Quiaca", "Cochinoca", "Palpalá", "Tilcara",
                "Santa Bárbara", "Humahuaca"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,opciones_departamento);
        spinner1.setAdapter(adapter);

    }

    public void llenarSpiner2(String[] opciones){
        spinner2.setEnabled(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,opciones);
        spinner2.setAdapter(adapter);
    }

    public void cambiarAFecha(View view){

        String valorDni = dni.getText().toString();
        String valorNom = nombre.getText().toString();
        String valorApe = apellido.getText().toString();
        String valorTel = telefono.getText().toString();
        String valordir = direccion.getText().toString();

        if (!valorDni.isEmpty() && !valorNom.isEmpty() && !valorApe.isEmpty() && !valorTel.isEmpty() && !valordir.isEmpty() &&
                spinner1.getSelectedItem().toString()!="seleccionar" && spinner2.getSelectedItem().toString()!="seleccionar"){

            //Toast.makeText(this,"PASA",Toast.LENGTH_SHORT).show();
            TurnoObj turno = new TurnoObj("DNIPasaporte",valorNom,valorApe,valorDni,valordir,valorTel,spinner2.getSelectedItem().toString());
            Intent pasar = new Intent(this, Fecha.class);
            pasar.putExtra("turno",turno);
            startActivity(pasar);

        }else {
            Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show();
        }

    }

}
